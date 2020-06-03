package com.lxlt.service.wxbrandservice;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 14:21
 */
public interface WxBrandService {

    /**
     * 根据 id 查询出所有的brand
     * @param brandId
     * @return
     */
    Map<String, Object> queryBrandDetailByBrandId(Integer brandId);

    /**
     * 分页查询出所有的brand
     * @param page
     * @param size
     * @return
     */
    Map<String, Object> queryBrand(Integer page, Integer size);
}
