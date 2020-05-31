package com.lxlt.service.logservice;

import com.lxlt.bean.logbean.LogReq;

import java.util.Map;

public interface LogService {
    Map<String,Object> listLog(LogReq logReq);
}
