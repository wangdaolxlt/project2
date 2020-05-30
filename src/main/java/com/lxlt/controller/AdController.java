package com.lxlt.controller;

import com.lxlt.bean.Ad;
import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.AdService;
import com.lxlt.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: UserController
 * @Author: admin
 * Date: 2020/5/29 14:28
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("admin/ad")
public class AdController {

    @Autowired
    AdService adService;

    @RequestMapping("list")
    public BaseRespVo list() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> hashMap = adService.queryAllAds();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        //HashMap<String, Object> hashMap = adService.addAd();
        return null;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Ad ad) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Ad adUpdate = adService.updateAd(ad);
        baseRespVo.setData(adUpdate);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Ad ad) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Ad adUpdate = adService.updateAd(ad);
        baseRespVo.setData(adUpdate);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
