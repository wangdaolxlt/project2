package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Region;
import com.lxlt.service.wxregionservice.WxRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 15:39
 */

@RestController
@RequestMapping("wx/region")
public class WxRegionController {

    @Autowired
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
