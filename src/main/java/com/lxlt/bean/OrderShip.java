package com.lxlt.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 * @PackgeName: OrderShip
 * @date: 2020/5/31
 * @Description:
 */
@Data
public class OrderShip {
    private Integer orderId;
    private String shipChannel;
    private String shipSn;
    private Date shipTime;
    private Integer status;

}
