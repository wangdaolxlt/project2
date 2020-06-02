package com.lxlt.service.wxindexservice;

import com.github.pagehelper.PageHelper;
import com.lxlt.bean.*;
import com.lxlt.bean.brandbean.Brand;
import com.lxlt.bean.wx.index.WxIndexFloorGoods;
import com.lxlt.bean.wx.index.WxIndexGroupon;
import com.lxlt.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/2 14:55
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    AdMapper adMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    TopicMapper topicMapper;

    @Override
    public Map<String, Object> index() {
        HashMap<String, Object> dataMap = new HashMap<>();
        // 查询商品
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false).andIsNewEqualTo(true);
        List<Goods> newGoodsList = goodsMapper.selectGoodsByExampleToIndex(goodsExample);
        dataMap.put("newGoodsList", newGoodsList);

        // 查询 优惠券
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andDeletedEqualTo(false);
        List<Coupon> couponList = couponMapper.selectCouponByExampleToIndex(couponExample);
        dataMap.put("couponList", couponList);

        // 查询 channel
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andDeletedEqualTo(false);
        List<Category> categoryList = categoryMapper.selectCategoryByExampleToIndex(categoryExample);
        dataMap.put("channel", categoryList);

        // 查询 grouponList
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andDeletedEqualTo(false);
        List<WxIndexGroupon> grouponList = grouponRulesMapper.selectGrouponRulesByExampleToIndex(grouponExample);
        // TODO: 2020/6/2 查询goods信息
        for (WxIndexGroupon wxIndexGroupon : grouponList) {
            Integer goodsId = wxIndexGroupon.getGoods().getId();
            GoodsExample grouponGoodsExample = new GoodsExample();
            grouponGoodsExample.createCriteria().andIdEqualTo(goodsId);
            List<Goods> goods = goodsMapper.selectGoodsByExampleToIndex(grouponGoodsExample);
            wxIndexGroupon.setGoods(goods.get(0));
        }

        dataMap.put("grouponList", grouponList);

        // 查询 banner 轮播图
        AdExample adExample = new AdExample();
        adExample.createCriteria().andDeletedEqualTo(false).andEnabledEqualTo(true);
        List<WxIndexGroupon> banner = adMapper.selectAdByExampleToIndex(adExample);
        dataMap.put("banner", banner);

        // 查询 品牌商 brandList
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(0);
        List<Brand> brandList = brandMapper.selectBrandByExampleToIndex(brandExample);
        dataMap.put("brandList", brandList);

        // 查询 hotgoods
        GoodsExample hotGoodsExample = new GoodsExample();
        hotGoodsExample.createCriteria().andDeletedEqualTo(false).andIsHotEqualTo(true);
        List<Goods> hotGoodsList = goodsMapper.selectGoodsByExampleToIndex(hotGoodsExample);
        dataMap.put("hotGoodsList", hotGoodsList);

        // 查询 topicList
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andDeletedEqualTo(false);
        List<Topic> topicList = topicMapper.selectTopicByExampleToIndex(topicExample);
        dataMap.put("topicList", topicList);

        // floorGoodsList
        // 限制查询数量
        PageHelper.startPage(1, 4);
        CategoryExample floorCategoryExample = new CategoryExample();
        floorCategoryExample.createCriteria().andDeletedEqualTo(false);
        List<WxIndexFloorGoods> floorGoodsList = categoryMapper.selectFloorGoodsByExampleToIndex(floorCategoryExample);

        for (WxIndexFloorGoods wxIndexFloorGoods : floorGoodsList) {
            Integer categoryId = wxIndexFloorGoods.getId();
            GoodsExample floorGoodsExample = new GoodsExample();
            floorGoodsExample.createCriteria().andDeletedEqualTo(false).andCategoryIdEqualTo(categoryId);
            List<Goods> goodsList = goodsMapper.selectGoodsByExampleToIndex(floorGoodsExample);
            wxIndexFloorGoods.setGoodsList(goodsList);
        }
        dataMap.put("floorGoodsList", floorGoodsList);

        return dataMap;
    }
}
