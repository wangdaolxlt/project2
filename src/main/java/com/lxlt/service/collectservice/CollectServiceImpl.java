package com.lxlt.service.collectservice;


import com.lxlt.bean.Collect;
import com.lxlt.bean.CollectExample;
import com.lxlt.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: AddressServiceImpl
 * @Author: admin
 * Date: 2020/5/29 15:02
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;

    @Override
    public HashMap<String, Object> queryAllCollections() {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andIdGreaterThan(0);

        int collectNum = (int) collectMapper.countByExample(collectExample);
        List<Collect> collectList = collectMapper.selectByExample(collectExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", collectList);
        hashMap.put("total", collectNum);
        return hashMap;
    }
}
