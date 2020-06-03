package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Region;
import com.lxlt.service.regionservice.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxRegionController
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */
@RestController
@RequestMapping("wx/region")
public class WxRegionController {

    @Autowired
    RegionService regionService;

    @RequestMapping("list")
    public BaseRespVo list(Region region){
        BaseRespVo<List<Region>> regionBaseRespVo = new BaseRespVo<>();
        regionBaseRespVo.setErrno(0);
        regionBaseRespVo.setErrmsg("成功");
        Integer pid = region.getPid();
        List<Region> allRegion = regionService.queryAllRegionByPid(pid);
        regionBaseRespVo.setData(allRegion);
        return regionBaseRespVo;
    }
}
