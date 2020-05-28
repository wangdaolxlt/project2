package com.lxlt.bean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean
 * @ClassName: BaseRespVo
 * @Author: admin
 * Date: 2020/5/28 19:50
 * project name: project2
 * @Version:
 * @Description:
 */

@Data
public class BaseRespVo<T> {


    /**
     * errno : 0
     * data : 8436ac0f-6de8-4697-a536-e9d8f1be1f9b
     * errmsg : 成功
     */

    private Integer errno;
    private T data;
    private String errmsg;

}
