package com.lxlt.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lenovo
 * @PackgeName: OrderRefound
 * @date: 2020/5/31
 * @Description:
 */
@Data
public class OrderRefound {
    private Integer orderId;
    private BigDecimal refundMoney;
}
