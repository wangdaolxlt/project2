package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Region;
import com.lxlt.service.regionservice.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 17:37
 */
@RestController
@RequestMapping("admin/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @RequestMapping("list")
    public BaseRespVo list(){
        BaseRespVo<List<Region>> regionBaseRespVo = new BaseRespVo<>();
        regionBaseRespVo.setErrno(0);
        regionBaseRespVo.setErrmsg("成功");
        List<Region> allRegion = regionService.queryAllRegionAndCategorize();
        regionBaseRespVo.setData(allRegion);
        return regionBaseRespVo;
    }
}
