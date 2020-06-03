package com.lxlt.service.wxfootpointservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.Footprint;
import com.lxlt.bean.FootprintExample;
import com.lxlt.bean.Goods;
import com.lxlt.bean.GoodsExample;
import com.lxlt.bean.wxfootprintbean.WxFootprint;
import com.lxlt.mapper.FootprintMapper;
import com.lxlt.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

@Service
public class WxFootprintServiceImpl implements WxFootprintService{
    @Autowired
    FootprintMapper footprintMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Map<String, Object> queryFootprint(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        HashMap<String, Object> map = new HashMap<>();
        List<WxFootprint> footprints = footprintMapper.selectByWxExample();
        PageInfo<WxFootprint> wxFootprintPageInfo = new PageInfo<>(footprints);
        long total = wxFootprintPageInfo.getSize();
        map.put("footprintList",footprints);
        return map;
    }

    @Override
    public Integer deleteFootprint(Integer id) {
        int i = footprintMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertFootPrint(Integer userId, Integer goodsId) {
        Footprint footprint = new Footprint();
        footprint.setUserId(userId);
        footprint.setGoodsId(goodsId);
        footprint.setAddTime(new Date());
        footprint.setUpdateTime(footprint.getAddTime());
        footprint.setDeleted(false);
        footprintMapper.insert(footprint);

    }
}
