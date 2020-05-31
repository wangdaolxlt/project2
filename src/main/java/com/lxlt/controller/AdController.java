package com.lxlt.controller;

import com.lxlt.bean.Ad;
import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.BaseRespVo2;
import com.lxlt.service.adservice.AdService;
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
    public BaseRespVo create(@RequestBody Ad ad) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Ad adCreate = adService.addAd(ad);
        baseRespVo.setData(adCreate);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
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
    public BaseRespVo2 delete(@RequestBody Ad ad) {
        BaseRespVo2 baseRespVo2 = new BaseRespVo2();
        Integer deleteAd = adService.deleteAd(ad);
        if (deleteAd > 0) {
            baseRespVo2.setErrno(0);
            baseRespVo2.setErrmsg("成功");
            return baseRespVo2;
        }
        return null;
    }
}
