package com.lxlt.service.wxgoodsservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.*;
import com.lxlt.bean.brandbean.Brand;
import com.lxlt.bean.issuebean.Issue;
import com.lxlt.bean.issuebean.IssueExample;
import com.lxlt.bean.wxgoodsbean.WxGoodsDetailBean;
import com.lxlt.bean.wxgoodsbean.WxGoodsListBean;
import com.lxlt.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    GoodsAttributeMapper attributeMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

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
        if (category.getPid() == 0) {
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
     * 查询当前分类或当前品牌的商品
     * @return
     */
    @Override
    public Map<String, Object> list(WxGoodsListBean wxGoodsListBean) {
        Integer page = wxGoodsListBean.getPage();
        Integer size = wxGoodsListBean.getSize();
        PageHelper.startPage(page, size);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria().andDeletedEqualTo(false);
        if (wxGoodsListBean.getCategoryId() != null){
            criteria.andCategoryIdEqualTo(wxGoodsListBean.getCategoryId());
        } else {
            criteria.andBrandIdEqualTo(wxGoodsListBean.getBrandId());
        }
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
    public Map<String, Object> goodsDetail(Integer goodsid) {
        HashMap<String, Object> dataMap = new HashMap<>();

        // 查询规格 specificationList
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsid);
        List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        ArrayList<WxGoodsDetailBean> wxGoodsDetailBeans = new ArrayList<>();
        out:
        for (GoodsSpecification goodsSpecification : goodsSpecifications) {
            // 是否已将当前规格添加到现有list中
            boolean sign = false;
            String name = goodsSpecification.getSpecification();
            for (WxGoodsDetailBean wxGoodsDetailBean : wxGoodsDetailBeans) {
                if (wxGoodsDetailBean.getName().equals(name)) {
                    wxGoodsDetailBean.getValueList().add(goodsSpecification);
                    sign = true;
                }
            }
            if (!sign) {
                WxGoodsDetailBean wxGoodsDetailBean = new WxGoodsDetailBean();
                wxGoodsDetailBean.setName(goodsSpecification.getSpecification());
                wxGoodsDetailBean.getValueList().add(goodsSpecification);
                wxGoodsDetailBeans.add(wxGoodsDetailBean);
            }

        }
        dataMap.put("specificationList", wxGoodsDetailBeans);

        // 查询团购
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        grouponRulesExample.createCriteria().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsid);
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        dataMap.put("groupon", grouponRules);

        // 查询issue
        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andDeletedEqualTo(false);
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        dataMap.put("issue", issues);

        // 查询userHasCollect
        // TODO: 2020/6/3 userHasCollect

        // 查询commet
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andDeletedEqualTo(false).andTypeEqualTo((byte) 0).andValueIdEqualTo(goodsid);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        HashMap<String, Object> comment = new HashMap<>();
        comment.put("data", comments);
        comment.put("conunt", comments.size());
        dataMap.put("comment", comment);

        // 查询attribute
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsid);
        List<GoodsAttribute> goodsAttributes = attributeMapper.selectByExample(goodsAttributeExample);
        dataMap.put("attribute", goodsAttributes);

        // 查询goods --> info
        Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
        dataMap.put("info", goods);

        // 查询brand
        Integer brandId = goods.getBrandId();
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        dataMap.put("brand", brand);

        // 查询productList
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProductExample.createCriteria().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsid);
        List<GoodsProduct> productList = goodsProductMapper.selectByExample(goodsProductExample);
        dataMap.put("productList", productList);

        return dataMap;
    }

    /**
     * 查询与goodsId相关的商品, 同一个Category中的商品
     *
     * @param goodsId 商品id
     * @return
     */
    @Override
    public Map<String, Object> related(Integer goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        Integer categoryId = goods.getCategoryId();

        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false).andCategoryIdEqualTo(categoryId).andIdNotEqualTo(goodsId);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        HashMap<String, Object> dataMpa = new HashMap<>();
        dataMpa.put("goodsList", goodsList);

        return dataMpa;
    }
}
