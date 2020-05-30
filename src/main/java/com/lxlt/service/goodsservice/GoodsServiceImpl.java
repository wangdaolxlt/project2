package com.lxlt.service.goodsservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.*;
import com.lxlt.bean.brandbean.SimpleBrand;
import com.lxlt.bean.goodsbean.GoodsDetailBean;
import com.lxlt.bean.goodsbean.QueryGoodsBean;
import com.lxlt.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 15:12
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public Map<String, Object> queryGoods(QueryGoodsBean queryGoodsBean) {
        PageHelper.startPage(queryGoodsBean.getPage(), queryGoodsBean.getLimit());
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 查询编号
        if(StringUtil.isNotEmpty(queryGoodsBean.getGoodsSn())){
            criteria.andGoodsSnEqualTo(queryGoodsBean.getGoodsSn());
        }
        // 名称模糊查询
        if(StringUtil.isNotEmpty(queryGoodsBean.getName())){
            criteria.andNameLike("%" + queryGoodsBean.getName() + "%");
        }
        goodsExample.setOrderByClause(queryGoodsBean.getSort() + " " + queryGoodsBean.getOrder());
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        long total = goodsPageInfo.getTotal();
        // 结果存入map
        HashMap<String, Object> goodMap = new HashMap<>();
        goodMap.put("total", total);
        goodMap.put("items", goods);
        return goodMap;

    }

    @Override
    public GoodsDetailBean goodsDetail(Integer goodsId) {
        GoodsDetailBean goodsDetailBean = new GoodsDetailBean();

        // goods
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        Goods goods = null;
        try {
            goods = goodsList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        goodsDetailBean.setGoods(goods);


        // 所属分类 categoryIds, 一级分类和二级分类
        Integer catCid = goods.getCategoryId();
        Integer catPid = categoryMapper.selectPidByid(catCid);
        goodsDetailBean.setCategoryIds(new Integer[]{catPid, catCid});

        // 商品参数 attribute
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<GoodsAttribute> attributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        goodsDetailBean.setAttributes(attributes);

        // 规格
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        goodsDetailBean.setSpecifications(specifications);

        // products
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProductExample.createCriteria().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<GoodsProduct> products = goodsProductMapper.selectByExample(goodsProductExample);
        goodsDetailBean.setProducts(products);

        return goodsDetailBean;
    }

    @Override
    public Map catAndBrand() {
        List<CategoryL1Data> categories = categoryMapper.selectL1("L1");
        for (CategoryL1Data levelOneCat  : categories) {
            String levelOneId = levelOneCat.getValue();
            List<CategoryL1Data> children = categoryMapper.selectCategoryByPid(levelOneId);
            levelOneCat.setChildren(children);
        }
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("categoryList", categories);

       List<SimpleBrand> simpleBrands = brandMapper.selectAllSimpleBrand();
       dataMap.put("brandList", simpleBrands);

        return dataMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(GoodsDetailBean goodsDetailBean) {
        Goods goods = goodsDetailBean.getGoods();
        List<GoodsSpecification> specifications = goodsDetailBean.getSpecifications();
        List<GoodsProduct> products = goodsDetailBean.getProducts();
        List<GoodsAttribute> attributes = goodsDetailBean.getAttributes();
        Date date = new Date();

        // 添加goods
        // 设置未传入的goods的默认值
        goods.setSortOrder((short) 1); // 设置sortOrder的默认值为1
        goods.setAddTime(date);
        goods.setUpdateTime(date);
        goods.setDeleted(false);
        int code = goodsMapper.insertSelective(goods);

        Integer goodsId = goods.getId();

        // 添加规格
        for (GoodsSpecification specification : specifications) {
            specification.setGoodsId(goodsId);
            specification.setAddTime(date);
            specification.setUpdateTime(date);
            specification.setDeleted(false);
            goodsSpecificationMapper.insertSelective(specification);
        }

        // 添加product
        for (GoodsProduct product : products) {
            product.setGoodsId(goodsId);
            product.setAddTime(date);
            product.setUpdateTime(date);
            product.setDeleted(false);
            goodsProductMapper.insertSelective(product);
        }

        // 添加attributes
        for (GoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goodsId);
            attribute.setAddTime(date);
            attribute.setUpdateTime(date);
            attribute.setDeleted(false);
            goodsAttributeMapper.insertSelective(attribute);
        }

        return 200;
    }

    /**
     * 根据商品id删除商品
     *  需要同步更新specification, product, attribute
     * @param goodsId 商品id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteGoodsById(Integer goodsId) {

        //逻辑删除goods
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setDeleted(true);
        int goodsCode = goodsMapper.updateByPrimaryKeySelective(goods);

        // 逻辑删除规格 specification
        GoodsSpecification specificationRecord = new GoodsSpecification();
        specificationRecord.setDeleted(true);
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(goodsId);
        int specCode = goodsSpecificationMapper.updateByExampleSelective(specificationRecord, goodsSpecificationExample);

        // 逻辑删除 product
        GoodsProduct goodsProductRecord = new GoodsProduct();
        goodsProductRecord.setDeleted(true);
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProductExample.createCriteria().andGoodsIdEqualTo(goodsId);
        int productCode = goodsProductMapper.updateByExampleSelective(goodsProductRecord, goodsProductExample);

        // 逻辑删除attribute
        GoodsAttribute goodsAttributeRecord = new GoodsAttribute();
        goodsAttributeRecord.setDeleted(true);
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(goodsId);
        int attrCode = goodsAttributeMapper.updateByExampleSelective(goodsAttributeRecord, goodsAttributeExample);

        return 200;
    }

    /**
     * 对商品进行更新
     * 将该商品所对应的信息全部逻辑删除
     * 更新商品,未删除的记录其deleted重新置为false
     *
     * @param goodsDetailBean
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(GoodsDetailBean goodsDetailBean) {
        Goods goods = goodsDetailBean.getGoods();
        List<GoodsSpecification> specifications = goodsDetailBean.getSpecifications();
        List<GoodsProduct> products = goodsDetailBean.getProducts();
        List<GoodsAttribute> attributes = goodsDetailBean.getAttributes();
        Integer goodsId = goods.getId();
        // 删除商品及其相关信息
        deleteGoodsById(goodsId);

        Date date = new Date();

        // 更新
        // 设置未传入的goods的默认值
            // 前端传入了该属性的值,但不应该对addtime进行更新,
        goods.setAddTime(null);
        int code = goodsMapper.updateByPrimaryKeySelective(goods);

        // 更新规格
        for (GoodsSpecification specification : specifications) {
            specification.setAddTime(null);
            goodsSpecificationMapper.updateByPrimaryKeySelective(specification);
        }

        // 更新product
        for (GoodsProduct product : products) {
            product.setAddTime(null);
            goodsProductMapper.updateByPrimaryKeySelective(product);
        }

        // 更新attributes
        for (GoodsAttribute attribute : attributes) {
            attribute.setAddTime(null);
            goodsAttributeMapper.updateByPrimaryKeySelective(attribute);
        }

        return 200;
    }
}
