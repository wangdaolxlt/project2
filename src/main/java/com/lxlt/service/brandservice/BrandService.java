package com.lxlt.service.brandservice;

import com.lxlt.bean.brandbean.QueryBrandBean;
import com.lxlt.bean.brandbean.Brand;

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
    Map<String, Object> queryAllBrand(QueryBrandBean queryBrandBean);

    int insertBrand(Brand brand);

    int deleteBrandById(Integer id);

    int updateBrandById(Brand brand);

}
