package com.lxlt.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.*;
import com.lxlt.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.lang.System;
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
    public Map<String, Object> queryOrderDetail() {
        return null;
    }













}
