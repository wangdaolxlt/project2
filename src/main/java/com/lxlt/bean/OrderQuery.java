package com.lxlt.bean;

import lombok.Data;

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

    private Short orderStatus;
    private String  sort;
    private String order;
}
