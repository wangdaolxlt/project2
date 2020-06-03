package com.lxlt.service.wxregionservice;

import com.lxlt.bean.Region;
import com.lxlt.bean.RegionExample;
import com.lxlt.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 15:46
 */
@Service
public class WxRegionServiceImpl extends WxRegionService {

    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<Region> selectRegionByPid(Integer pid) {
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andPidEqualTo(pid);
        List<Region> regionList = regionMapper.selectByExample(regionExample);

        return regionList;
    }
}
