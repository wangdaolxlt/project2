package com.lxlt.service.statservice;

import com.lxlt.bean.statbean.StatGoods;
import com.lxlt.bean.statbean.StatOrder;
import com.lxlt.bean.statbean.StatUser;
import com.lxlt.mapper.OrderGoodsMapper;
import com.lxlt.mapper.OrderMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Map<String, Object> userStat() {
        HashMap<String, Object> map = new HashMap<>();
        String[] columns = {"day","users"};
        map.put("columns",columns);
        //查询当天新增用户，根据add_time按照日期进行分组
        List<StatUser> statUsers = userMapper.selectUsers();
        map.put("rows",statUsers);
        return map;
    }
    //商品统计 订单量 下单货品数量 下单货品总额
    @Override
    public Map<String, Object> goodsStat() {
        HashMap<String, Object> map = new HashMap<>();
        String[] columns = {"day","orders","products","amount"};
        map.put("columns",columns);
        List<StatGoods> statGoods = orderGoodsMapper.selectGoods();
        map.put("rows",statGoods);
        return map;
    }
    //订单统计，根据订单创建时间排序，
    // 查询下单用户数、订单量、订单总额(实付费用)、客单价=订单总额/下单用户数 (保留两位小数)
    @Override
    public Map<String, Object> orderStat() {
        HashMap<String, Object> map = new HashMap<>();
        String[] columns = {"day","orders","customers","amount","pcr"};
        map.put("columns",columns);
        List<StatOrder> statOrders = orderMapper.selectOrder();
        map.put("rows",statOrders);
        return map;
    }
}
