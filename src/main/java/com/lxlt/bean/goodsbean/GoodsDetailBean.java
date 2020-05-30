package com.lxlt.bean.goodsbean;

import com.lxlt.bean.*;
import lombok.Data;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 22:20
 *
 * 用于存储商品详细信息的bean
 * 包含goods, categoryIds, specification, attribute
 */

@Data
public class GoodsDetailBean {
    private Integer[] categoryIds;
    private Goods goods;
    private List<GoodsSpecification> specifications;
    private List<GoodsProduct> products;
    private List<GoodsAttribute> attributes;
}
