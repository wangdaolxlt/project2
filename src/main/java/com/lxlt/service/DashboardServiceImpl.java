package com.lxlt.service;


import com.lxlt.bean.*;
import com.lxlt.mapper.GoodsMapper;
import com.lxlt.mapper.GoodsProductMapper;
import com.lxlt.mapper.OrderMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: DashboardServiceImpl
 * @Author: admin
 * Date: 2020/5/28 23:27
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class DashboardServiceImpl implements DashboradService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

     @Override
     public HashMap<String, Integer> queryAllCounts() {
         //查询用户数量
         UserExample userExample = new UserExample();
         userExample.createCriteria().andIdGreaterThan(0);
         int userNum = (int) userMapper.countByExample(userExample);

         //查询订单数量
         OrderExample orderExample = new OrderExample();
         orderExample.createCriteria().andIdGreaterThan(0);
         int orderNum = (int) orderMapper.countByExample(orderExample);

         //查询商品数量
         GoodsExample goodsExample = new GoodsExample();
         goodsExample.createCriteria().andIdGreaterThan(0);
         int goodsNum = (int) goodsMapper.countByExample(goodsExample);

         //查询货品数量
         GoodsProductExample goodsProductExample = new GoodsProductExample();
         goodsProductExample.createCriteria().andIdGreaterThan(0);
         int productNum = (int) goodsProductMapper.countByExample(goodsProductExample);

         HashMap<String, Integer> map = new HashMap<>();
         map.put("userTotal", userNum);
         map.put("orderTotal", orderNum);
         map.put("goodsTotal", goodsNum);
         map.put("productTotal", productNum);
         return map;
     }
}
