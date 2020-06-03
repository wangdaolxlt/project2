package com.lxlt.bean;

import lombok.Data;

import java.util.List;

/**
 * @author lenovo
 * @PackgeName: OrderQuery
 * @date: 2020/5/30
 * @Description:
 */
@Data
public class OrderQuery {
    private Integer page;
    private Integer limit;
    private Integer userId;

    private String orderSn;

    private List<Short> orderStatusArray;
    private String  sort;
    private String order;
}
