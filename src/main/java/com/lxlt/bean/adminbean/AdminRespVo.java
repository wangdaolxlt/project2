package com.lxlt.bean.adminbean;

import lombok.Data;

@Data
public class AdminRespVo<T> {

    private Integer errno;
    private T data;
    private String errmsg;
}
