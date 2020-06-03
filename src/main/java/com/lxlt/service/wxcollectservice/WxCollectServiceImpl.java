package com.lxlt.service.wxcollectservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.*;
import com.lxlt.bean.wxcollectbean.WxCollect;
import com.lxlt.bean.wxcollectbean.WxCollectReqVo;
import com.lxlt.mapper.CollectMapper;
import com.lxlt.mapper.GoodsMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @PackgeName: com.lxlt.service.wxcollectservice
 * @ClassName: WxCollectServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/4 2:47
 * project name: project2
 */
@Service
public class WxCollectServiceImpl implements WxCollectService{
    @Autowired
    CollectMapper collectMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;
    @Override
    public Map collectList(String username, WxCollectReqVo wxCollectReqVo) {
        HashMap map = new HashMap();
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        criteria.andTypeEqualTo(wxCollectReqVo.getType());
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        ArrayList wxCollectList = new ArrayList();
        for (Collect collect : collects) {
            WxCollect wxCollect = new WxCollect();
            wxCollect.setId(collect.getId());
            wxCollect.setValueId(collect.getValueId());
            wxCollect.setType(collect.getType());
            //查找goods
            Goods goods = goodsMapper.selectByPrimaryKey(collect.getValueId());
            wxCollect.setBrief(goods.getBrief());
            wxCollect.setPicUrl(goods.getPicUrl());
            wxCollect.setName(goods.getName());
            wxCollect.setRetailPrice(goods.getRetailPrice());
            wxCollectList.add(wxCollect);
        }

        //分页显示
        PageHelper.startPage(wxCollectReqVo.getPage(),wxCollectReqVo.getSize());
        PageInfo<WxCollect> wxCollectInfo = new PageInfo<>(wxCollectList);
        map.put("collectList",wxCollectList);
        map.put("totalPages",wxCollectInfo.getTotal());
        return map;
    }

    @Override
    public Map collectAddOrDelete(String username, Collect collect) {
        HashMap map = new HashMap();
        //先通过username查到user的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();

        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andDeletedEqualTo(false).andTypeEqualTo(collect.getType()).andUserIdEqualTo(userId).andValueIdEqualTo(collect.getValueId());
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        //如果没有收藏记录则创建，并且说明这个请求是add
        if(collects.size() == 0){
            collect.setAddTime(new Date());
            collect.setUpdateTime(new Date());
            collect.setDeleted(false);
            collect.setUserId(userId);
            collectMapper.insertSelective(collect);
            map.put("type", "add");
            return map;
        }
        //如果有记录，那么就是取消收藏，即将deleted改成1
        collects.get(0).setUpdateTime(new Date());
        collects.get(0).setDeleted(true);
        collectMapper.updateByPrimaryKey(collects.get(0));
        map.put("type", "delete");
        return map;
    }
}
