package com.lxlt.service.goodsservice;

import com.lxlt.bean.goodsbean.GoodsDetailBean;
import com.lxlt.bean.goodsbean.QueryGoodsBean;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 15:07
 */
public interface GoodsService {
    /**
     * 查询goods
     * @param queryGoodsBean
     * @return
     */
    Map<String, Object> queryGoods(QueryGoodsBean queryGoodsBean);

    /**
     * 查询goods详细信息
     * @param goodssId
     * @return
     */
    GoodsDetailBean goodsDetail(Integer goodssId);

    /**
     * 查询分类cat和品牌brand信息
     * @return
     */
    Map catAndBrand();

    /**
     * 创建一个goods
     *  需要同步更新specification, product, attribute
     * @param goodsDetailBean
     * @return
     */
    int create(GoodsDetailBean goodsDetailBean);

    /**
     * 根据商品id删除商品
     *  需要同步更新specification, product, attribute
     * @param goodsId 商品id
     * @return
     */
    int deleteGoodsById(Integer goodsId);

    /**
     * 对商品进行更新
     *  将该商品所对应的信息全部逻辑删除
     *  更新商品,未删除的记录其deleted重新置为false
     * @return
     * @param goodsDetailBean
     */
    int update(GoodsDetailBean goodsDetailBean);
}
