package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Category;
import com.lxlt.bean.CategoryListData;
import com.lxlt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody CategoryListData categoryListData){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        try {
            categoryService.delete(categoryListData);
        }catch (Exception e){
            e.printStackTrace();
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody Category requestCategory){
        BaseRespVo<Category> baseRespVo = new BaseRespVo<>();
        Category category = categoryService.create(requestCategory);
        if(category == null){
            baseRespVo.setErrno(401);
            baseRespVo.setErrmsg("禁止创建没有L1主类目的L2类目");
            return baseRespVo;
        }
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        baseRespVo.setData(category);
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Category requestCategory){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        try{
            categoryService.update(requestCategory);
        }catch (Exception e){
            e.printStackTrace();
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
}
