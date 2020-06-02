package com.lxlt.controller.admin;

import com.lxlt.bean.*;
import com.lxlt.bean.goodsbean.GoodsDetailBean;
import com.lxlt.bean.goodsbean.QueryGoodsBean;
import com.lxlt.service.categoryservice.CategoryService;
import com.lxlt.service.goodsservice.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 14:58
 */
@RestController
@RequestMapping("admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("list")
    public BaseRespVo queryGoods(QueryGoodsBean queryGoodsBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map<String, Object> goodsMap = goodsService.queryGoods(queryGoodsBean);
        if (goodsMap == null) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(goodsMap);
        return baseRespVo;
    }

    /**
     * 新增商品
     * @return
     */
    @RequestMapping("create")
    public BaseRespVo create(@RequestBody GoodsDetailBean goodsDetailBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = goodsService.create(goodsDetailBean);
        if(code != 200){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    /**
     * 更新商品
     *
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody GoodsDetailBean goodsDetailBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = goodsService.update(goodsDetailBean);
        if(code != 200){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    /**
     * 删除商品,需要删除如下内容
     *  goods, specification, product, attribute
     *
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Goods goods){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = goodsService.deleteGoodsById(goods.getId());
        if (code != 200){
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("detail")
    public BaseRespVo detail(Integer id){
        BaseRespVo baseRespVo = new BaseRespVo();
        GoodsDetailBean goodsDetailBean = goodsService.goodsDetail(id);
        if (goodsDetailBean == null) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(goodsDetailBean);
        return baseRespVo;
    }

    @RequestMapping("catAndBrand")
    public BaseRespVo catAndBrand(){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map dataMap = goodsService.catAndBrand();
        baseRespVo.setData(dataMap);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
}
