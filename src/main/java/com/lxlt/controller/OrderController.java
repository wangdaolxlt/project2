package com.lxlt.controller;


import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.OrderQuery;
import com.lxlt.service.orderservice.OrderService;
import com.lxlt.bean.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lenovo
 * @PackgeName: OrderController
 * @date: 2020/5/28
 * @Description:
 */
@RestController
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @RequestMapping("list")
    public BaseRespVo list(OrderQuery orderQuery){
        BaseRespVo<Map<String, Object>> orderBaseRespVo = new BaseRespVo<>();
        Map<String, Object> resultMap = orderService.queryAllOrder(orderQuery);
        orderBaseRespVo.setData(resultMap);
        orderBaseRespVo.setErrmsg("成功");
        orderBaseRespVo.setErrno(0);
        return orderBaseRespVo;
    }
    @RequestMapping("detail")
    public BaseRespVo detail(Integer id){
        BaseRespVo<Map<String, Object>> detailBaseResoVo = new BaseRespVo<>();
        Map<String, Object> resultMap1 = orderService.queryOrderDetail(id);
        detailBaseResoVo.setData(resultMap1);
        detailBaseResoVo.setErrmsg("成功");
        detailBaseResoVo.setErrno(0);
        return detailBaseResoVo;
    }

    @RequestMapping("/refund")
    public BaseRespVo refund(@RequestBody OrderRefound orderRefound){
        orderService.refund(orderRefound);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
    @RequestMapping("/ship")
    public BaseRespVo ship(@RequestBody OrderShip orderShip){
        boolean ship = orderService.ship(orderShip);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
}
