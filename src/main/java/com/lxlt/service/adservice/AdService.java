package com.lxlt.service.adservice;

import com.lxlt.bean.Ad;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: UserService
 * @Author: admin
 * Date: 2020/5/29 11:31
 * project name: project2
 * @Version:
 * @Description:
 */
public interface AdService {

    HashMap<String, Object> queryAllAds();

    Ad addAd(Ad ad);

    Ad updateAd(Ad ad);

    Integer deleteAd(Ad ad);
}
