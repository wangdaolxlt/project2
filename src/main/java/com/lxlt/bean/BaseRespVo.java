package com.lxlt.bean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean
 * @ClassName: BaseRespVo
 * @Author: Li Haiquan
 * Date: 2020/5/28 16:53
 * project name: project2
 */

@Data
public class BaseRespVo<T> {

    /**
     * errno : 0
     * data : 2700ebd4-73aa-4066-8a92-819ba14b57a9
     * errmsg : 成功
     */

    private Integer errno;
    private T data;
    private String errmsg;

}
