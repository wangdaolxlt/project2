package com.lxlt.bean.wxcollectbean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @PackgeName: com.lxlt.bean.wxcollectbean
 * @ClassName: WxCollect
 * @Author: Li Haiquan
 * Date: 2020/6/4 3:06
 * project name: project2
 */
@Data
public class WxCollect {

    private Integer valueId;

    private Integer id;

    private Byte type;

    private String brief;

    private String picUrl;

    private String name;

    private BigDecimal retailPrice;
}
