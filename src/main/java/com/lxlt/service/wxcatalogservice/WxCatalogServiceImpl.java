package com.lxlt.service.wxcatalogservice;

import com.lxlt.bean.Category;
import com.lxlt.bean.CategoryExample;
import com.lxlt.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxcatalogservice
 * @ClassName: WxCatalogServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/1 20:17
 * project name: project2
 */
@Service
public class WxCatalogServiceImpl implements WxCatalogService{
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Map cartIndex() {
        Map<String, Object> map = new HashMap();
        //取出最小id
        int id = categoryMapper.selectMinId();
        //currentCategory
        Category category = categoryMapper.selectByPrimaryKey(id);
        map.put("currentCategory", category);
        //categoryList
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andPidEqualTo(0);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        map.put("categoryList", categoryList);
        //currentSubCategory
        CategoryExample categoryExample1 = new CategoryExample();
        categoryExample1.createCriteria().andPidEqualTo(id).andDeletedEqualTo(false);
        List<Category> currentSubCategory = categoryMapper.selectByExample(categoryExample1);
        map.put("currentSubCategory", currentSubCategory);
        return map;
    }

    @Override
    public Map currentCatalog(int id) {
        Map<String, Object> map = new HashMap();
        //currentCategory
        Category category = categoryMapper.selectByPrimaryKey(id);
        map.put("currentCategory", category);
        //currentSubCategory
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(id).andDeletedEqualTo(false);
        List<Category> currentSubCategory = categoryMapper.selectByExample(categoryExample);
        map.put("currentSubCategory", currentSubCategory);
        return map;
    }
}
