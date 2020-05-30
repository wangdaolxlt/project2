package com.lxlt.bean;

import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/30 10:23
 */
@Data
public class BaseQueryBean {
    /**
     * 排序字段
     */
    public String sort;
    /**
     * 排序方式,正序或者逆序
     */
    public String order;
    /**
     * 分页查询的当前页码
     */
    public Integer page;
    /**
     * 分页查询,每页记录的数目
     */
    public Integer limit;
}
