package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.goodsservice.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/2 21:29
 */
@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("count")
    public BaseRespVo count(){
        BaseRespVo<Long> baseRespVo = new BaseRespVo<>();
        Long goodsCount = goodsService.goodsCount();
        if(goodsCount == null){
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(goodsCount);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");

        return baseRespVo;
    }



}
