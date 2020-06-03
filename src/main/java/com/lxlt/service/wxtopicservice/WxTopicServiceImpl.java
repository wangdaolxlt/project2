package com.lxlt.service.wxtopicservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.*;
import com.lxlt.mapper.GoodsMapper;
import com.lxlt.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxtopicservice
 * @ClassName: WxTopicServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/3 11:35
 * project name: project2
 */
@Service
public class WxTopicServiceImpl implements WxTopicService{
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Map wxQueryTopic(WxBaseQueryBean queryBean) {
        PageHelper.startPage(queryBean.getPage(), queryBean.getSize());
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        Map<String, Object> topicMap = new HashMap<>();
        topicMap.put("data", topics);
        topicMap.put("count", total);
        return topicMap;
    }

    @Override
    public Map wxTopicDetail(int id) {
        Map map = new HashMap();
        Topic topic = topicMapper.selectByPrimaryKey(id);
        map.put("topic", topic);
        List goodsId = topic.getGoods();
        if(goodsId.size() == 0){
            map.put("goods", null);
            return map;
        }
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false).andIdIn(goodsId);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        map.put("goods", goods);
        return null;
    }

    @Override
    public List<Topic> wxTopicRelated(int id) {
        List topicList = new ArrayList();
        ArrayList idList = new ArrayList();
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andIdGreaterThan(id);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        if(topics.size() >= 4){
            for (int i = 0; i < 4; i++) {
                topicList.add(topics.get(i));
            }
        }else {
            for (Topic topic : topics) {
                topicList.add(topic);
            }
        }
        return topicList;
    }
}
