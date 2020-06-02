package com.lxlt.service.grouponrulesservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.Goods;
import com.lxlt.bean.GoodsExample;
import com.lxlt.bean.GrouponRules;
import com.lxlt.bean.GrouponRulesExample;
import com.lxlt.bean.grouponbean.QueryGrouponRulesBean;
import com.lxlt.mapper.GoodsMapper;
import com.lxlt.mapper.GrouponRulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/30 11:17
 */
@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Map<String, Object> queryGrouponRules(QueryGrouponRulesBean queryGrouponRulesBean) {
        // 开启分页查询
        PageHelper.startPage(queryGrouponRulesBean.getPage(), queryGrouponRulesBean.getLimit());
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        // 未删除的数据
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        // id查找
        if (queryGrouponRulesBean.getGoodsId() != null) {
            criteria.andGoodsIdEqualTo(queryGrouponRulesBean.getGoodsId());
        }
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        PageInfo<GrouponRules> grouponRulesPageInfo = new PageInfo<>(grouponRules);
        long total = grouponRulesPageInfo.getTotal();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", grouponRules);
        return dataMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createGrouponRules(GrouponRules grouponRules) {
        // 查询是否存在goodsid
        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
        // 不存在该商品,返回
        if (goods == null) {
            return 400;
        }
        // 一定存在该商品, 获得商品的名字
        String goodsName = goods.getName();
        String picUrl = goods.getPicUrl();

        // 默认数据封装进参数
        Date date = new Date();
        grouponRules.setPicUrl(picUrl);
        grouponRules.setAddTime(date);
        grouponRules.setGoodsName(goodsName);
        grouponRules.setUpdateTime(date);
        grouponRules.setDeleted(false);
        grouponRulesMapper.insertSelective(grouponRules);
        grouponRules = grouponRulesMapper.selectByPrimaryKey(grouponRules.getId());
        return 200;
    }

    /**
     * 更新groupRules,需要判断goodsId是否存在
     *
     * @param grouponRules
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(GrouponRules grouponRules) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(grouponRules.getGoodsId());
        long l = goodsMapper.countByExample(goodsExample);
        // 数据不存在, 直接返回
        if (l == 0) {
            return 400;
        }
        int code = grouponRulesMapper.updateByPrimaryKey(grouponRules);
        if (code == 1) {
            return 200;
        }
        return 500;
    }

    /**
     * 删除groupRules
     *
     * @param grouponRules
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(GrouponRules grouponRules) {
        Integer id = grouponRules.getId();
        // 逻辑删除
        GrouponRules recode = new GrouponRules();
        recode.setId(id);
        recode.setDeleted(true);
        int code = grouponRulesMapper.updateByPrimaryKeySelective(recode);
        if(code == 1){
            return 200;
        }
        return 500;
    }

}

