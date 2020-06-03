package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Region;

import com.lxlt.service.regionservice.RegionService;

import com.lxlt.service.wxregionservice.WxRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
<<<<<<< HEAD
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxRegionController
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:

=======
 * @Author: Lucas_Alison
 * Date: 2020/6/3 15:39
 */


@RestController
@RequestMapping("wx/region")
public class WxRegionController {

    @Autowired

//    RegionService regionService;
//
//    @RequestMapping("list")
//    public BaseRespVo list(Region region){
//        BaseRespVo<List<Region>> regionBaseRespVo = new BaseRespVo<>();
//        regionBaseRespVo.setErrno(0);
//        regionBaseRespVo.setErrmsg("成功");
//        Integer pid = region.getPid();
//        List<Region> allRegion = regionService.queryAllRegionByPid(pid);
//        regionBaseRespVo.setData(allRegion);
//        return regionBaseRespVo;
//    }

    WxRegionService wxRegionService;

    @RequestMapping("list")
    public BaseRespVo list(Integer pid){
        BaseRespVo<List<Region>> baseRespVo = new BaseRespVo<>();
        List<Region> regionList = wxRegionService.selectRegionByPid(pid);

        if (regionList == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(500);
            return baseRespVo;
        }

        baseRespVo.setData(regionList);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
    
}
