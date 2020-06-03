package com.lxlt.service.wxcollectservice;

import com.lxlt.bean.Collect;
import com.lxlt.bean.wxcollectbean.WxCollectReqVo;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxcollectservice
 * @ClassName: WxCollectService
 * @Author: Li Haiquan
 * Date: 2020/6/4 2:46
 * project name: project2
 */
public interface WxCollectService {
    Map collectList(String username, WxCollectReqVo wxCollectReqVo);

    Map collectAddOrDelete(String username, Collect collect);
}
