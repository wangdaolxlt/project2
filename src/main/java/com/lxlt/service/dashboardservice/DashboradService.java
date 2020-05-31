package com.lxlt.service.dashboardservice;

import com.lxlt.bean.BaseRespVo;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: DashboradService
 * @Author: admin
 * Date: 2020/5/28 23:27
 * project name: project2
 * @Version:
 * @Description:
 */
public interface DashboradService {
    HashMap<String, Integer> queryAllCounts();
}
