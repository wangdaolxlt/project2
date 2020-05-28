package com.lxlt.bean;

import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 16:43
 */

@Data
public class BaseRespVo<T> {

    /**
     * errno : 0
     * data : 6cfc3d7c-bf85-4886-811f-0e3ea550c72a
     * errmsg : 成功
     */

    private int errno;
    private T data;
    private String errmsg;

}
