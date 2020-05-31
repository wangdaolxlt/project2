package com.lxlt.bean.statbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StatGoods {
    //下单总金额
    private Double amount;
    //订单量
    private Integer orders;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;
    //下单货品数量
    private Integer products;
}
