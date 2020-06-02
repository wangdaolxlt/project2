package com.lxlt.service.configservice;

import com.lxlt.bean.System;
import com.lxlt.bean.configbean.ConfigBean;
import com.lxlt.bean.configbean.ConfigRequest;
import com.lxlt.mapper.ConfigMapper;
import com.lxlt.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @PackgeName: ConfigServiceImpl
 * @date: 2020/6/1
 * @Description:
 */
@Service
public class ConfigServiceImpl implements  ConfigService {
    @Autowired
    ConfigMapper configMapper;

    @Autowired
    SystemMapper systemMapper;

    @Override
    public List<ConfigBean> getConfig(String type){
        List<ConfigBean> configBeanList = configMapper.getConfig(type);
        return configBeanList;
    }

    @Override
    public Boolean updateConfig(Map<String,String> map){

        List<System> systems = ConfigRequest.toSystemS(map);
        for (System system : systems) {
            int rows = systemMapper.insert(system);
            if (rows != 1){
                return false;
            }
        }
        return true;
    }
}
