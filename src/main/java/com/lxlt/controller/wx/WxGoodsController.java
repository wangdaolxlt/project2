package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.wxgoodsbean.WxGoodsListQueryBean;
import com.lxlt.service.wxgoodsservice.WxGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/2 21:29
 */
@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    WxGoodsService wxGoodsService;

    @RequestMapping("count")
    public BaseRespVo count(){
        BaseRespVo<Long> baseRespVo = new BaseRespVo<>();
        Long goodsCount = wxGoodsService.goodsCount();
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

    @RequestMapping("category")
    public BaseRespVo category(Integer id){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map<String, Object> dataMap = wxGoodsService.queryCategoryForWx(id);
        if (dataMap == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }
        baseRespVo.setData(dataMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;

    }


    @RequestMapping("list")
    // public BaseRespVo list(Integer categoryId, Integer page, Integer size){
    public BaseRespVo list(WxGoodsListQueryBean wxGoodsListQueryBean){
        BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo<>();
        Map<String, Object> dataMap = wxGoodsService.list(wxGoodsListQueryBean);
        if (dataMap == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }

        baseRespVo.setData(dataMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;

    }

    @RequestMapping("detail")
    public BaseRespVo goodsDetail(Integer id){
        BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo<>();
        Map<String, Object> dataMap = wxGoodsService.goodsDetail(id);
        if (dataMap == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }
        // 访问商品成功, 浏览记录进行添加


        baseRespVo.setData(dataMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;

    }

    @RequestMapping("related")
    public BaseRespVo related(Integer id){
        BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo<>();
        Map<String, Object> dataMap = wxGoodsService.related(id);
        if (dataMap == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }

        baseRespVo.setData(dataMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;

    }
}
