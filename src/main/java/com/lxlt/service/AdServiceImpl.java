package com.lxlt.service;



import com.lxlt.bean.Ad;
import com.lxlt.bean.AdExample;

import com.lxlt.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;

    @Override
    public HashMap<String, Object> queryAllAds() {
        AdExample adExample = new AdExample();
        adExample.createCriteria().andIdGreaterThan(0);

        int adNum = (int) adMapper.countByExample(adExample);
        List<Ad> adList = adMapper.selectByExample(adExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", adList);
        hashMap.put("total", adNum);
        return hashMap;
    }




    @Override
    public Ad updateAd(Ad ad) {
        Integer id = ad.getId();
        ad.setUpdateTime(new Date());
        AdExample adExample = new AdExample();
        adExample.createCriteria().andIdEqualTo(id);
        adMapper.updateByExample(ad, adExample);
        return ad;
    }
}
