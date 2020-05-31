package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.statservice.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    @RequestMapping("user")
    public BaseRespVo userStat(){
        BaseRespVo userRespVo = new BaseRespVo();
        Map<String, Object> map = statService.userStat();
        userRespVo.setErrno(0);
        userRespVo.setErrmsg("成功");
        userRespVo.setData(map);
        return userRespVo;
    }

    @RequestMapping("goods")
    public BaseRespVo goodsStat(){
        BaseRespVo goodsRespVo = new BaseRespVo();
        Map<String, Object> map = statService.goodsStat();
        goodsRespVo.setErrno(0);
        goodsRespVo.setErrmsg("成功");
        goodsRespVo.setData(map);
        return goodsRespVo;
    }

    @RequestMapping("order")
    public BaseRespVo orderStat() {
        BaseRespVo orderRespVo = new BaseRespVo();
        Map<String, Object> map = statService.orderStat();
        orderRespVo.setErrno(0);
        orderRespVo.setErrmsg("成功");
        orderRespVo.setData(map);
        return orderRespVo;
    }
}
