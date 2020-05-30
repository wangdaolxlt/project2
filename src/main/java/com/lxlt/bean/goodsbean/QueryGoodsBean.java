package com.lxlt.bean.goodsbean;

import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 15:04
 *
 * 查询goods使用的bean
 *  用于接收goods查询参数
 */
@Data
public class QueryGoodsBean {
    /**
     * 分页查询的当前页码
     */
    private Integer page;
    /**
     * 分页查询,每页记录的数目
     */
    private Integer limit;
    /**
     * 商品编号
     */
    private String goodsSn;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式,正序或者逆序
     */
    private String order;
}
