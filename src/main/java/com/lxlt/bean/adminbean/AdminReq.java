package com.lxlt.bean.adminbean;

import lombok.Data;

@Data
public class AdminReq {
    Integer page;
    Integer limit;
    String username;
    String sort;
    String order;
}
