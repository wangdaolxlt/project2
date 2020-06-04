package com.lxlt.service.wxcartservice;

import com.lxlt.bean.*;
import com.lxlt.bean.addressbean.Address;
import com.lxlt.bean.cartbean.CartCheckedReqVo;
import com.lxlt.bean.cartbean.CartCheckoutReqVo;
import com.lxlt.bean.cartbean.CartCheckoutRespVo;
import com.lxlt.bean.cartbean.CartTotal;
import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @PackgeName: com.lxlt.service.wxcartservice
 * @ClassName: WxCartServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/2 9:41
 * project name: project2
 */
@Service
public class WxCartServiceImpl implements WxCartService{
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponMapper couponMapper;

    @Override
    public Map cartIndex(String username) {
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 1) {
            return null;
        }
        User user = users.get(0);
        int userId = user.getId();
        //全部购物车
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        int goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); i++) {
            goodsCount += cartList.get(i).getNumber();
            goodsAmount = goodsAmount.add(cartList.get(i).getPrice().multiply(new BigDecimal(cartList.get(i).getNumber())));
        }

        //被checked的商品
        criteria.andCheckedEqualTo(true);
        List<Cart> checkedCartList = cartMapper.selectByExample(cartExample);
        int checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0);
        for (int i = 0; i < checkedCartList.size(); i++) {
            checkedGoodsCount += checkedCartList.get(i).getNumber();
            checkedGoodsAmount = checkedGoodsAmount.add(checkedCartList.get(i).getPrice().multiply(new BigDecimal(checkedCartList.get(i).getNumber())));
        }
        CartTotal cartTotal = new CartTotal(goodsCount, goodsAmount, checkedGoodsCount, checkedGoodsAmount);
        Map map = new HashMap();
        map.put("cartTotal", cartTotal);
        map.put("cartList", cartList);
        return map;
    }

    @Override
    public int cartGoodsCount(String username) {
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();
        //全部购物车
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andDeletedEqualTo(false);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        int goodsCount = 0;
        for (int i = 0; i < cartList.size(); i++) {
            goodsCount += cartList.get(i).getNumber();
        }
        return goodsCount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map cartChecked(String username, CartCheckedReqVo cartCheckedReqVo) {

        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();
        //更新product的checked
        List productIds = cartCheckedReqVo.getProductIds();
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        criteria.andProductIdIn(productIds);
        Cart cart = new Cart();
        cart.setUpdateTime(new Date());
        if(cartCheckedReqVo.getIsChecked() == 1){
            cart.setChecked(true);
        }else {
            cart.setChecked(false);
        }
        cartMapper.updateByExampleSelective(cart, cartExample);
        //返回和首页一样的数据
        return cartIndex(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cartUpdate(Cart cart) {
        //判断商品是否下架
        Integer goodsId = cart.getGoodsId();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(! goods.getIsOnSale()) return 710;
        //判断购物车修改数量是否超过库存
        Integer productId = cart.getProductId();
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
        if(cart.getNumber() > goodsProduct.getNumber()) return 711;
        //正常更新
        int code = cartMapper.updateByPrimaryKeySelective(cart);
        if(code != 1){
            return 502;
        }
        return 200;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map cartDelete(String username, CartCheckedReqVo cartCheckedReqVo) {
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();
        //更新时间和deleted的状态
        List productIds = cartCheckedReqVo.getProductIds();
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        criteria.andProductIdIn(productIds);
        Cart cart = new Cart();
        cart.setUpdateTime(new Date());
        cart.setDeleted(true);
        cartMapper.updateByExampleSelective(cart, cartExample);
        //返回和首页一样的数据
        return cartIndex(username);
    }

    @Override
    public Map cartAdd(String username, Cart cart) {
        HashMap<String, Object> map = new HashMap();
        //判断商品是否下架
        Integer goodsId = cart.getGoodsId();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(! goods.getIsOnSale()) {
            map.put("errno", 710);
            return map;
        }
        //判断购物车修改数量是否超过库存
        Integer productId = cart.getProductId();
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
        if(cart.getNumber() > goodsProduct.getNumber()) {
            map.put("errno", 711);
            return map;
        }
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();
        //先判断元购物车是否有相同productId的cart
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        criteria.andProductIdEqualTo(productId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if(carts.size() != 0){
            Integer firstCartId = carts.get(0).getId();
            Integer totalNum = carts.get(0).getNumber();
            //合并多条之前为合并的cart
/*            for (int i = 0; i < carts.size(); i++) {
                totalNum += carts.get(i).getNumber();
                CartExample cartExample1 = new CartExample();
                Cart cart1 = new Cart();
                cart1.setId(carts.get(i).getId());
                cart1.setUpdateTime(new Date());
                cart1.setDeleted(true);
                cartMapper.updateByPrimaryKeySelective(cart1);
            }*/

            totalNum += cart.getNumber();
            if(totalNum > goodsProduct.getNumber()){
                map.put("error", 711);
                return map;
            }
            cart.setId(firstCartId);
            cart.setNumber(totalNum);
            cartMapper.updateByPrimaryKeySelective(cart);
            map.put("errno", 0);
            map.put("cartGoodsCount", cartGoodsCount(username));
        }else {
            //正常新增
            cart.setDeleted(false);
            cart.setChecked(true);
            Date date = new Date();
            cart.setAddTime(date);
            cart.setUpdateTime(date);
            cart.setUserId(userId);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName(goods.getName());
            cart.setPrice(goodsProduct.getPrice());
            cart.setSpecifications(goodsProduct.getSpecifications());
            if(!StringUtils.isEmpty(goodsProduct.getUrl())){
                cart.setPicUrl(goodsProduct.getUrl());
            }else {
                cart.setPicUrl(goods.getPicUrl());
            }
            int code = cartMapper.insertSelective(cart);
            if(code != 1){
                map.put("errno", 502);
                return map;
            }
            map.put("errno", 0);
            map.put("cartGoodsCount", cartGoodsCount(username));
            map.put("cartId", cart.getId());
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map cartFastAdd(String username, Cart cart) {
        HashMap<String, Object> map = new HashMap();
        //判断商品是否下架
        Integer goodsId = cart.getGoodsId();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(! goods.getIsOnSale()) {
            map.put("errno", 710);
            return map;
        }
        //判断购物车修改数量是否超过库存
        Integer productId = cart.getProductId();
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
        if(cart.getNumber() > goodsProduct.getNumber()) {
            map.put("errno", 711);
            return map;
        }
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();
        //正常新增
        cart.setDeleted(false);
        cart.setChecked(true);
        Date date = new Date();
        cart.setAddTime(date);
        cart.setUpdateTime(date);
        cart.setUserId(userId);
        cart.setGoodsSn(goods.getGoodsSn());
        cart.setGoodsName(goods.getName());
        cart.setPrice(goodsProduct.getPrice());
        cart.setSpecifications(goodsProduct.getSpecifications());
        if(!StringUtils.isEmpty(goodsProduct.getUrl())){
            cart.setPicUrl(goodsProduct.getUrl());
        }else {
            cart.setPicUrl(goods.getPicUrl());
        }
        int code = cartMapper.insertSelective(cart);
        if(code != 1){
            map.put("errno", 502);
            return map;
        }
        map.put("errno", 0);
        map.put("cartId", cart.getId());
        return map;
    }


    @Override
    public CartCheckoutRespVo checkout(String username, CartCheckoutReqVo cartCheckoutReqVo) {

        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();

        CartCheckoutRespVo cartCheckoutRespVo = new CartCheckoutRespVo();
        //团购不需要做
        cartCheckoutRespVo.setGrouponPrice(0);
        cartCheckoutRespVo.setGrouponRulesId(0);

        //checkedAddress
        AddressExample addressExample = new AddressExample();
        if (cartCheckoutReqVo.getAddressId() == 0) {
            addressExample.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
            List<Address> addressList = addressMapper.selectByExample(addressExample);
            Address address = addressList.size() >0 ? addressList.get(0) : null;
            cartCheckoutRespVo.setCheckedAddress(address);
        } else {
            Address address = addressMapper.selectByPrimaryKey(cartCheckoutReqVo.getAddressId());
            cartCheckoutRespVo.setCheckedAddress(address);
        }

        // 获取商品和总价
        BigDecimal goodsTotalPrice = new BigDecimal("0");
        ArrayList<Cart> checkedGoodsList = new ArrayList<>();
        if (cartCheckoutReqVo.getCartId() != 0) {
            // 立即支付(单品) checkout模式
            Cart cart = cartMapper.selectByPrimaryKey(cartCheckoutReqVo.getCartId());
            BigDecimal number = new BigDecimal(cart.getNumber().toString());
            BigDecimal price = cart.getPrice();
            goodsTotalPrice = price.multiply(number);
            // 修改deleted = true
            cart.setDeleted(true);
            cartMapper.updateByPrimaryKeySelective(cart);
            // 放入cart
            checkedGoodsList.add(cart);
        } else {
            // 其他情况（查询全部goods, 条件为userId+deleted+checked）
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andUserIdEqualTo(userId).andCheckedEqualTo(true).andDeletedEqualTo(false);
            List<Cart> cartList = cartMapper.selectByExample(cartExample);
            for (Cart cart : cartList) {
                BigDecimal price = cart.getPrice();
                BigDecimal number = new BigDecimal(cart.getNumber().toString());
                goodsTotalPrice = goodsTotalPrice.add(price.multiply(number));
                // 修改deleted = true
                cart.setDeleted(true);
                cartMapper.updateByPrimaryKeySelective(cart);
            }
            // 全部放入list
            checkedGoodsList.addAll(cartList);
        }
        // 优惠券  coupon_user
        double couponPrice = 0;
        Short availableCouponLength = 0;
        if (cartCheckoutReqVo.getCouponId() != 0) {
            Coupon coupon = couponMapper.selectByPrimaryKey(cartCheckoutReqVo.getCouponId());
            BigDecimal discount = coupon.getDiscount();
            couponPrice = discount.doubleValue();
            availableCouponLength = coupon.getDays();
        }
        // 运费 还没设计 system
        double freightPrice = 0;
        // 订单费用 = 商品总价 + 运费 - 优惠券价格
        Double orderTotalPrice = goodsTotalPrice.doubleValue() + freightPrice - couponPrice;
        // 实付费用 = 订单价格 - 团购优惠
        Double integralPrice = 0d;
        Double actualPrice = orderTotalPrice - integralPrice;
        cartCheckoutRespVo.setActualPrice(actualPrice);
        cartCheckoutRespVo.setOrderTotalPrice(orderTotalPrice);
        cartCheckoutRespVo.setCouponPrice(couponPrice);
        cartCheckoutRespVo.setAvailableCouponLength(availableCouponLength);
        cartCheckoutRespVo.setCouponId(cartCheckoutReqVo.getCouponId());
        cartCheckoutRespVo.setFreightPrice(freightPrice);
        cartCheckoutRespVo.setCheckedGoodsList(checkedGoodsList);
        cartCheckoutRespVo.setGoodsTotalPrice(goodsTotalPrice);
        cartCheckoutRespVo.setAddressId(cartCheckoutReqVo.getAddressId());
        return cartCheckoutRespVo;
    }


}
