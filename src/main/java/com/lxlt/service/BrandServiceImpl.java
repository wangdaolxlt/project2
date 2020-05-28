package com.lxlt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.Brand;
import com.lxlt.bean.BrandExample;
import com.lxlt.bean.BrandQuery;
import com.lxlt.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 0:10
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Map<String, Object> queryAllBrand(BrandQuery brandQuery) {
        PageHelper.startPage(brandQuery.getPage(), brandQuery.getLimit());
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        // 如果传入id参数则查询id，否则不限制id
        if(brandQuery.getId() != null){
            criteria.andIdEqualTo(brandQuery.getId());
        }
        // 如果传入name则按条件查找,不限制name
        if(StringUtil.isNotEmpty(brandQuery.getName())){
            criteria.andNameLike("%" + brandQuery.getName() + "%");
        }
        // 对结果进行排序
        brandExample.setOrderByClause(brandQuery.getSort() + " " + brandQuery.getOrder());
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        long total = brandPageInfo.getTotal();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("items", brands);
        return resultMap;
    }
}
