package com.lxlt.service.wxtopicservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.Topic;
import com.lxlt.bean.TopicExample;
import com.lxlt.bean.WxBaseQueryBean;
import com.lxlt.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
