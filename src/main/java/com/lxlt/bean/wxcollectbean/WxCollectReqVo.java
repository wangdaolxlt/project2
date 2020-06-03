package com.lxlt.bean.wxcollectbean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.wxcollectbean
 * @ClassName: WxCollectReqVo
 * @Author: Li Haiquan
 * Date: 2020/6/4 2:50
 * project name: project2
 */
@Data
public class WxCollectReqVo {
    Byte type;
    Integer size;
    Integer page;
}
