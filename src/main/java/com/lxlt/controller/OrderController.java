package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Order;
import com.lxlt.bean.OrderQuery;
import com.lxlt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public BaseRespVo detail(){
        BaseRespVo<Map<String, Object>> detailBaseResoVo = new BaseRespVo<>();
        Map<String, Object> resultMap1 = orderService.queryOrderDetail();
        detailBaseResoVo.setData(resultMap1);
        detailBaseResoVo.setErrmsg("成功");
        detailBaseResoVo.setErrno(0);
        return detailBaseResoVo;
    }

}
