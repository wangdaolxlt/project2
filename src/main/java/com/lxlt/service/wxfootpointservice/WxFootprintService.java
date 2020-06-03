package com.lxlt.service.wxfootpointservice;

import java.util.Map;

public interface WxFootprintService {
    Map<String, Object> queryFootprint(Integer page, Integer size);

    Integer deleteFootprint(Integer id);

    void insertFootPrint(Integer userId, Integer goodsId);
}
