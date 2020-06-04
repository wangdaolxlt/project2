package com.lxlt.service.wxauthservice;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.service.wxauthservice
 * @ClassName: WxAuthService
 * @Author: Li Haiquan
 * Date: 2020/6/4 10:09
 * project name: project2
 */
public interface WxAuthService {
    HashMap<String, Object> getUserInfo(String username);
}
