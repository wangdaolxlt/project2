package com.lxlt.service;

import com.lxlt.bean.BrandQuery;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 23:23
 */
public interface BrandService {

    /**
     * 分页查询所有的品牌制造上
     * @return 查询结果的map
     */
    Map<String, Object> queryAllBrand(BrandQuery brandQuery);
}
