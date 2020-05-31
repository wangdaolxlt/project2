package com.lxlt.bean.statbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StatOrder {
    //订单总额
    private Double amount;
    //订单量
    private Integer orders;
    //下单时间
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date day;
    //下单用户
    private Integer customers;
    //客单价
    private Double pcr;
}
