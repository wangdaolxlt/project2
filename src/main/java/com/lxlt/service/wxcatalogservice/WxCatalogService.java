package com.lxlt.service.wxcatalogservice;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxcatalogservice
 * @ClassName: WxCatalogService
 * @Author: Li Haiquan
 * Date: 2020/6/1 20:17
 * project name: project2
 */
public interface WxCatalogService {
    Map cartIndex();

    Map currentCatalog(int id);
}
