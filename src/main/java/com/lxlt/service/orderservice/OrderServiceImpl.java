package com.lxlt.service.orderservice;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.*;
import com.lxlt.mapper.GoodsProductMapper;
import com.lxlt.mapper.OrderGoodsMapper;
import com.lxlt.mapper.OrderMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.lang.System;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @PackgeName: OrderServiceImpl
 * @date: 2020/5/29
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    /**
     *
     */
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsProductMapper productMapper;

    @Override
    public Map<String, Object> queryAllOrder(OrderQuery orderQuery) {
        PageHelper.startPage(orderQuery.getPage(), orderQuery.getLimit());
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        //如果id非空，根据id查询
        if (orderQuery.getUserId() != null) {
            criteria.andUserIdEqualTo(orderQuery.getUserId());
        }
        //如果订单号非空
        if (StringUtil.isNotEmpty(orderQuery.getOrderSn())) {
            criteria.andOrderSnLike("%" + orderQuery.getOrderSn() + "%");
        }
        //如果订单状态查询
        if (orderQuery.getOrderStatus() != null) {
            criteria.andOrderStatusEqualTo(orderQuery.getOrderStatus());
        }
        //排序
        criteria.andDeletedEqualTo(false);
        orderExample.setOrderByClause(orderQuery.getSort() + " " + orderQuery.getOrder());
        List<Order> orders = orderMapper.selectByExample(orderExample);

        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        long total = orderPageInfo.getTotal();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("items", orders);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryOrderDetail(Integer id) {
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(id);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);
        Order order = orderMapper.selectByPrimaryKey(id);
        User user = userMapper.selectUserById(order.getUserId());
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderGoods",orderGoodsList);
        resultMap.put("user",user);
        resultMap.put("order",order);
        return resultMap;
    }

    @Transactional
    @Override
    public void refund(OrderRefound orderRefound){
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(orderRefound.getOrderId());
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);
        for (OrderGoods orderGoods : orderGoodsList) {
            GoodsProduct goodsProduct = productMapper.selectByPrimaryKey(orderGoods.getProductId());
            goodsProduct.setNumber(goodsProduct.getNumber()+orderGoods.getNumber());
            goodsProduct.setUpdateTime(new Date());
            productMapper.updateByPrimaryKeySelective(goodsProduct);
        }

        Date date = new Date();
        Order order = new Order();
        order.setId(orderRefound.getOrderId());
        order.setOrderStatus((short)203);
        order.setUpdateTime(date);
        order.setEndTime(date);
        orderMapper.updateByPrimaryKeySelective(order);
    }
    @Override
    public boolean ship(OrderShip orderShip){
        Date date = new Date();
        orderShip.setShipTime(date);
        orderShip.setStatus(301);
        orderMapper.ship(orderShip);
        return true;
    }
}
