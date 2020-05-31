package com.lxlt.bean.logbean;

import lombok.Data;

@Data
public class LogReq {
    Integer page;
    Integer limit;
    String sort;
    String order;
}
