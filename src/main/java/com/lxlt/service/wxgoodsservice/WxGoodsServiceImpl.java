package com.lxlt.service.wxgoodsservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.Category;
import com.lxlt.bean.CategoryExample;
import com.lxlt.bean.Goods;
import com.lxlt.bean.GoodsExample;
import com.lxlt.mapper.CategoryMapper;
import com.lxlt.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 15:12
 */

@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询goods的数量
     *
     * @return
     */
    @Override
    public Long goodsCount() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        long l = goodsMapper.countByExample(goodsExample);
        return l;
    }

    /**
     * 查询catgory, currentCategory, brotherCategory, parentCategory
     *
     * @return
     */
    @Override
    public Map<String, Object> queryCategoryForWx(Integer id) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Category category = categoryMapper.selectByPrimaryKey(id);

        Category currentCategory;
        List<Category> brotherCategory;
        Category parentCategory;

        //是否是一级分类
        if (category.getPid() == 0){
            parentCategory = category;

            //查询父分类的所有子分类
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andDeletedEqualTo(false).andPidEqualTo(id);
            brotherCategory = categoryMapper.selectByExample(categoryExample);
            // 取第一个作为当前category
            currentCategory = brotherCategory.get(0);

        } else {
            currentCategory = category;
            Integer categoryPid = currentCategory.getPid();
            parentCategory = categoryMapper.selectByPrimaryKey(categoryPid);

            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andPidEqualTo(categoryPid);
            brotherCategory = categoryMapper.selectByExample(categoryExample);

        }
        dataMap.put("parentCategory", parentCategory);
        dataMap.put("currentCategory", currentCategory);
        dataMap.put("brotherCategory", brotherCategory);

        return dataMap;
    }

    /**
     * 查询当前分类的商品
     *
     * @param categoryId 当前分类
     * @param page       当前页数
     * @param size       每页数据显示内容
     * @return
     */
    @Override
    public Map<String, Object> list(Integer categoryId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false).andCategoryIdEqualTo(categoryId);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        long count = goodsPageInfo.getTotal();

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("goodsList", goodsList);
        dataMap.put("count", count);
        // TODO: 2020/6/2 map中添加filterCategoryList, 具体作用是?
        return dataMap;
    }

    /**
     * 获得商品详情
     *
     * @return
     */
    @Override
    public Map<String, Object> goodsDetail(Integer id) {
        HashMap<String, Object> dataMap = new HashMap<>();

        // 查询规格 specificationList
        // 查询优惠券
        // 查询issue
        // 查询userMasCollect
        // 查询commet
        // 查询attribute
        // 查询brand
        // 查询productList
        // 查询info







        return null;
    }
}
