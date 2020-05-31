package com.lxlt.service.footprintservice;

import com.lxlt.bean.Footprint;
import com.lxlt.bean.FootprintExample;
import com.lxlt.mapper.FootprintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: FootprintService
 * @Author: admin
 * Date: 2020/5/29 15:36
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class FootprintServiceImpl implements FootprintService{

    @Autowired
    FootprintMapper footprintMapper;

    @Override
    public HashMap<String, Object> queryAllFootprints() {
        FootprintExample footprintExample = new FootprintExample();
        footprintExample.createCriteria().andIdGreaterThan(0);

        int footprintNum = (int) footprintMapper.countByExample(footprintExample);
        List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", footprintList);
        hashMap.put("total", footprintNum);
        return hashMap;
    }
}
