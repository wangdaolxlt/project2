package com.lxlt.service;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Order;
import com.lxlt.bean.OrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @PackgeName: OrderService
 * @date: 2020/5/29
 * @Description:
 */
public interface OrderService {
    /**
     * 查询所有订单
     * @return
     */

    Map<String, Object> queryAllOrder(OrderQuery orderQuery);
    /**
     * 查看详情
     * @return
     */
    Map<String,Object> queryOrderDetail();
}
