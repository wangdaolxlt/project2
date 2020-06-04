package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Result;
import com.lxlt.service.wxfootpointservice.WxFootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/footprint")
public class WxFootprintController {

    @Autowired
    WxFootprintService wxFootprintService;

    @RequestMapping("list")
    public BaseRespVo footprintList(Integer page,Integer size){
        BaseRespVo<Map<String,Object>> baseRespVo = new BaseRespVo();
        Map<String,Object> map = wxFootprintService.queryFootprint(page,size);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(map);
        return baseRespVo;
    }
    //删除足迹
    @RequestMapping("delete")
    public Result deleteFootprint(Integer id){
        Result result = new Result();
        Integer integer = wxFootprintService.deleteFootprint(id);
        if(integer == 1) {
            result.setErrno(0);
            result.setErrmsg("成功");
        }else {
            result.setErrno(10000);
            result.setErrmsg("删除失败");
        }
        return result;
    }
}
