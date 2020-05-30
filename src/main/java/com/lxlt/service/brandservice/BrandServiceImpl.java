package com.lxlt.service.brandservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.brandbean.Brand;
import com.lxlt.bean.BrandExample;
import com.lxlt.bean.brandbean.QueryBrandBean;
import com.lxlt.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Map<String, Object> queryAllBrand(QueryBrandBean queryBrandBean) {
        PageHelper.startPage(queryBrandBean.getPage(), queryBrandBean.getLimit());
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        criteria.andDeletedEqualTo(0);
        // 如果传入id参数则查询id，否则不限制id
        if(queryBrandBean.getId() != null){
            criteria.andIdEqualTo(queryBrandBean.getId());
        }
        // 如果传入name则按条件查找,不限制name
        if(StringUtil.isNotEmpty(queryBrandBean.getName())){
            criteria.andNameLike("%" + queryBrandBean.getName() + "%");
        }
        // 对结果进行排序
        brandExample.setOrderByClause(queryBrandBean.getSort() + " " + queryBrandBean.getOrder());
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        long total = brandPageInfo.getTotal();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("items", brands);
        return resultMap;
    }

    @Transactional
    @Override
    public int insertBrand(Brand brand) {
        int code = brandMapper.insertSelectiveAndReturnId(brand);
        if (code != 1) {
            return 500;
        }
        return 200;
    }

    @Transactional
    @Override
    public int deleteBrandById(Integer id) {
        // 删除即更新
        Brand brand = new Brand();
        brand.setId(id);
        brand.setDeleted(true);
        int code = brandMapper.updateByPrimaryKeySelective(brand);
        if(code != 1){
            return 500;
        }
        return 200;
    }

    @Transactional
    @Override
    public int updateBrandById(Brand brand) {
        int code = brandMapper.updateByPrimaryKeySelective(brand);
        if (code != 1) {
            return 500;
        }
        return 200;
    }
}
