package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: CategoryController
 * @Author: Li Haiquan
 * Date: 2020/5/28 19:59
 * project name: project2
 */
@RestController
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("list")
    public BaseRespVo list(){
        BaseRespVo baseRespVo= categoryService.selectByLevel("L1");
        return baseRespVo;
    }

    @RequestMapping("l1")
    public BaseRespVo l1(){
        BaseRespVo baseRespVo = categoryService.selectL1("L1");
        return baseRespVo;
    }
}
