package com.lxlt.service;

import com.lxlt.bean.Region;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 17:44
 */
public interface RegionService {

    /**
     * 查询所有的Region
     * @return
     */
    List<Region> queryAllRegionAndCategorize();
}
