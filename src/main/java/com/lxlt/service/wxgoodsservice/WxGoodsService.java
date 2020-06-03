package com.lxlt.service.wxgoodsservice;

import com.lxlt.bean.Goods;
import com.lxlt.bean.goodsbean.GoodsDetailBean;
import com.lxlt.bean.goodsbean.QueryGoodsBean;

import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 15:07
 */
public interface WxGoodsService {

    /**
     * 查询goods的数量
     * @return
     */
    Long goodsCount();

    /**
     * 查询catgory, currentCategory, brotherCategory, parentCategory
     * @return
     * @param id
     */
    Map<String, Object> queryCategoryForWx(Integer id);

    /**
     * 查询当前分类的商品
     * @param categoryId 当前分类
     * @param page 当前页数
     * @param size 每页数据显示内容
     * @return
     */
    Map<String, Object> list(Integer categoryId, Integer page, Integer size);

    /**
     * 获得商品详情
     * @return
     * @param goodsId
     */
    Map<String, Object> goodsDetail(Integer goodsId);

    /**
     * 查询与goodsId相关的商品, 同一个Category中的商品
     * @param goodsId 商品id
     * @return
     */
    Map<String, Object> related(Integer goodsId);
}