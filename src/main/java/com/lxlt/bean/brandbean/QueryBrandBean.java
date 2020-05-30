package com.lxlt.bean.brandbean;

import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 0:56
 */
@Data
public class QueryBrandBean {
    private Integer page;
    private Integer limit;
    private Integer id;
    private String name;
    private String  sort;
    private String order;
}
