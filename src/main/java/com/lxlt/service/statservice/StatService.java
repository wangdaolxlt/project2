package com.lxlt.service.statservice;

import java.util.Map;

public interface StatService {
    //用户统计
    Map<String,Object> userStat();
    //商品统计
    Map<String,Object> goodsStat();
    //订单统计
    Map<String,Object> orderStat();

}
