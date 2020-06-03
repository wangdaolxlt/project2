package com.lxlt.service.wxregionservice;

import com.lxlt.bean.Region;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 15:45
 */
public abstract class WxRegionService {
    public abstract List<Region> selectRegionByPid(Integer pid);
}
