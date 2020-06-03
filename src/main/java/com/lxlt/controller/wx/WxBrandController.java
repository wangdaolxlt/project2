package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.mapper.BrandMapper;
import com.lxlt.service.brandservice.BrandService;
import com.lxlt.service.wxbrandservice.WxBrandService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 13:02
 */
@RestController
@RequestMapping("wx/brand")
public class WxBrandController {

    @Autowired
    WxBrandService wxBrandService;


    @RequestMapping("brand")
    public BaseRespVo detail(Integer id){
        // BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo();
        // Map<String, Object> dataMap = wxBrandService.queryBrandByBrandId(id);
        // if (dataMap == null) {
        //     baseRespVo.setErrmsg("服务器内部错误");
        //     baseRespVo.setErrno(500);
        //     return baseRespVo;
        // }
        //
        // baseRespVo.setData(dataMap);
        // baseRespVo.setErrno(0);
        // baseRespVo.setErrmsg("成功");
        // return baseRespVo;
        return null;

    }
}
