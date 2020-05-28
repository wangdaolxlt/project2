package com.lxlt.service;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.CategoryL1Data;
import com.lxlt.bean.CategoryListData;
import com.lxlt.mapper.CategoryMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: CategoryServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/5/28 21:14
 * project name: project2
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public BaseRespVo selectByLevel(String level) {
        List<CategoryListData> categoryListData = categoryMapper.selectByLevel("L1");
        BaseRespVo<List> categoryListDataBaseRespVo = new BaseRespVo<>();
        categoryListDataBaseRespVo.setErrmsg("成功");
        categoryListDataBaseRespVo.setErrno(0);
        categoryListDataBaseRespVo.setData(categoryListData);
        return categoryListDataBaseRespVo;
    }

    @Override
    public BaseRespVo selectL1(String level) {
        List<CategoryL1Data> l1Data = categoryMapper.selectL1("L1");
        BaseRespVo<List> categoryListDataBaseRespVo = new BaseRespVo<>();
        categoryListDataBaseRespVo.setErrmsg("成功");
        categoryListDataBaseRespVo.setData(l1Data);
        categoryListDataBaseRespVo.setErrno(0);
        return categoryListDataBaseRespVo;
    }
}
