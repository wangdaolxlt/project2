package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.brandbean.QueryBrandBean;
import com.lxlt.bean.brandbean.Brand;
import com.lxlt.service.brandservice.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
    public BaseRespVo list(QueryBrandBean queryBrandBean){
        BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo<>();
        Map<String, Object> resultMap = brandService.queryAllBrand(queryBrandBean);
        baseRespVo.setData(resultMap);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody Brand brand){
        BaseRespVo<Brand> baseRespVo = new BaseRespVo<>();
        Date date = new Date();
        brand.setAddTime(date);
        brand.setUpdateTime(date);
        int code = brandService.insertBrand(brand);
        if(code != 200){
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(brand);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Brand brand){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = brandService.deleteBrandById(brand.getId());
        if(code != 200){
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Brand brand){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = brandService.updateBrandById(brand);
        if(code != 200){
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }






}
