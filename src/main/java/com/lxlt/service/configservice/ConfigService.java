package com.lxlt.service.configservice;

import com.lxlt.bean.configbean.ConfigBean;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @PackgeName: ConfigService
 * @date: 2020/6/1
 * @Description:
 */
public interface ConfigService {
    List<ConfigBean> getConfig(String type);
    Boolean updateConfig(Map<String,String> map);
}
