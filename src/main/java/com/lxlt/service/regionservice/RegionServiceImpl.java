package com.lxlt.service.regionservice;

import com.lxlt.bean.Region;
import com.lxlt.bean.RegionExample;
import com.lxlt.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 18:00
 */
@Service
public class  RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;


    @Override
    public List<Region> queryAllRegionAndCategorize() {
        RegionExample firstRegionExample = new RegionExample();
        firstRegionExample.createCriteria().andTypeEqualTo((byte) 1);
        List<Region> allRegion = regionMapper.selectByExample(firstRegionExample);
        for (Region region : allRegion) {
            Integer secondLevelCode = region.getCode();
            RegionExample secondLevelRegionExample = new RegionExample();
            // mysql 的between and 是一个闭区间
            secondLevelRegionExample.createCriteria().andCodeBetween(secondLevelCode * 100, secondLevelCode * 100 + 99).andTypeEqualTo((byte) 2);
            List<Region> secondLevelReigons = regionMapper.selectByExample(secondLevelRegionExample);
            for (Region secondLevelRegion : secondLevelReigons) {
                Integer thirdLevelCode = secondLevelRegion.getCode();
                RegionExample thirdLevelRegionExample = new RegionExample();
                thirdLevelRegionExample.createCriteria().andCodeBetween(thirdLevelCode * 100, thirdLevelCode * 100 + 99).andTypeEqualTo((byte) 3);
                List<Region> thirdLevelRegions = regionMapper.selectByExample(thirdLevelRegionExample);
                secondLevelRegion.setChildren(thirdLevelRegions);
            }
            region.setChildren(secondLevelReigons);

        }
        return allRegion;
    }


}
