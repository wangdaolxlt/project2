package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.BrandQuery;
import com.lxlt.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 23:20
 */

@RestController
@RequestMapping("admin/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    public BaseRespVo list(BrandQuery brandQuery){
        BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo<>();
        Map<String, Object> resultMap = brandService.queryAllBrand(brandQuery);
        baseRespVo.setData(resultMap);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);

        return baseRespVo;
    }
}
