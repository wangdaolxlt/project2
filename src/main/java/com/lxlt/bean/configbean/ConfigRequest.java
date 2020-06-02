package com.lxlt.bean.configbean;
import com.lxlt.bean.System;
import lombok.Data;

import java.util.*;

/**
 * @author lenovo
 * @PackgeName: ConfigRequest
 * @date: 2020/6/1
 * @Description:
 */
@Data
public class ConfigRequest {
    public static List<System> toSystemS(Map<String, String> map){
        List<System> systemList = new ArrayList<>();
        Date date = new Date();

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System system = new System(entry.getKey(), entry.getValue(), date, date, false);
            systemList.add(system);
        }
        return systemList;
    }
}
