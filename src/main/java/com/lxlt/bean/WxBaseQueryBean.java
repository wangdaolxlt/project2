package com.lxlt.bean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean
 * @ClassName: WxBaseQueryBean
 * @Author: Li Haiquan
 * Date: 2020/6/3 11:44
 * project name: project2
 */
@Data
public class WxBaseQueryBean {
/*    *//**
     * 排序字段
     *//*
    public String sort;
    *//**
     * 排序方式,正序或者逆序
     *//*
    public String order;*/
    /**
     * 分页查询的当前页码
     */
    public Integer page;
    /**
     * 分页查询,每页记录的数目
     */
    public Integer size;
}
