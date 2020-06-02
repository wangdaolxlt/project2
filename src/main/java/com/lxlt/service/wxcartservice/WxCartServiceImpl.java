package com.lxlt.service.wxcartservice;

import com.lxlt.bean.Cart;
import com.lxlt.bean.CartExample;
import com.lxlt.bean.User;
import com.lxlt.bean.UserExample;
import com.lxlt.bean.cartbean.CartCheckedReqVo;
import com.lxlt.bean.cartbean.CartTotal;
import com.lxlt.mapper.CartMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
