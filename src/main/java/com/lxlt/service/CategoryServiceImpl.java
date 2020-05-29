package com.lxlt.service;

import com.lxlt.bean.*;
import com.lxlt.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
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

    @Override
    @Transactional
    public void delete(CategoryListData categoryListData) {
        CategoryExample categoryExample = new CategoryExample();
        //children为空，只需要将本身的deleted从0置为1
        if(categoryListData.getChildren() == null){
            categoryExample.createCriteria().andIdEqualTo(categoryListData.getId());
            Category category = new Category();
            category.setUpdateTime(new Date());
            category.setDeleted(true);
            categoryMapper.updateByExampleSelective(category,categoryExample);
        }else {
            //children不为空，需要将children中的id全部取出来，将他们的deleted从0置为1
            List<Integer> list = new LinkedList<>();
            list.add(categoryListData.getId());
            for (int i = 0; i < categoryListData.getChildren().size(); i++) {
                list.add(categoryListData.getChildren().get(i).getId());
            }
            categoryExample.createCriteria().andIdIn(list);
            Category category = new Category();
            category.setDeleted(true);
            category.setUpdateTime(new Date());
            categoryMapper.updateByExampleSelective(category,categoryExample);
        }
    }

    @Override
    @Transactional
    public Category create(Category requestCategory) {
        //先对requestCategory进行校验，level为L2时 ，不允许pid为0
        if("L2".equals(requestCategory.getLevel()) && requestCategory.getPid() == 0 ){
            return null;
        }
        //插入数据
        Date date = new Date();
        requestCategory.setAddTime(date);
        requestCategory.setUpdateTime(date);
        requestCategory.setDeleted(false);
        categoryMapper.insertSelective(requestCategory);
        //取出新数据的id
        requestCategory.setId(categoryMapper.getLastInsertId());
        return requestCategory;
    }

    @Override
    @Transactional
    public void update(Category requestCategory) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(requestCategory.getId());
        requestCategory.setUpdateTime(new Date());
        categoryMapper.updateByExampleSelective(requestCategory,categoryExample);
    }


}
