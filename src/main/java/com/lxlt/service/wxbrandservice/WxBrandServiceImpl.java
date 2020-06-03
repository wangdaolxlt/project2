package com.lxlt.service.wxbrandservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.BrandExample;
import com.lxlt.bean.brandbean.Brand;
import com.lxlt.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 14:35
 */
@Service
public class WxBrandServiceImpl implements WxBrandService {

    @Autowired
    BrandMapper brandMapper;

    /**
     * 根据 id 查询出所有的brand
     *
     * @param brandId
     * @return
     */
    @Override
    public Map<String, Object> queryBrandDetailByBrandId(Integer brandId) {
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("brand", brand);

        return dataMap;
    }

    /**
     * 分页查询出所有的brand
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> queryBrand(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(0).andIdIsNotNull();
        List<Brand> brandList = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);
        long total = brandPageInfo.getTotal();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("totalPages", total);
        dataMap.put("brandList", brandList);

        return dataMap;
    }
}
