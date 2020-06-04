package com.lxlt.service.topicservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.Topic;
import com.lxlt.bean.TopicExample;
import com.lxlt.bean.topicbean.TopicQueryBean;
import com.lxlt.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.topicservice
 * @ClassName: TopicServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/5/30 15:19
 * project name: project2
 */
@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    TopicMapper topicMapper;

    @Override
    public Map queryTopic(TopicQueryBean topicQueryBean) {
        PageHelper.startPage(topicQueryBean.getPage(), topicQueryBean.getLimit());
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        //专题标题模糊查询
        if(StringUtil.isNotEmpty(topicQueryBean.getTitle())){
            criteria.andTitleLike("%" + topicQueryBean.getTitle() + "%");
        }
        //专题子标题模糊查询
        if(StringUtil.isNotEmpty(topicQueryBean.getSubtitle())){
            criteria.andSubtitleLike("%" + topicQueryBean.getSubtitle() + "%");
        }
        topicExample.setOrderByClause(topicQueryBean.getSort() + " " + topicQueryBean.getOrder());
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        Map<String, Object> topicMap = new HashMap<>();
        topicMap.put("items", topics);
        topicMap.put("total", total);
        return topicMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Topic createTopic(Topic requestTopic) {
        //添加默认参数
        Date date = new Date();
        requestTopic.setSortOrder(0);
        requestTopic.setUpdateTime(date);
        requestTopic.setAddTime(date);
        requestTopic.setDeleted(false);
        topicMapper.insertSelective(requestTopic);
        return requestTopic;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Topic updateTopic(Topic requestTopic) {
        requestTopic.setUpdateTime(new Date());
        topicMapper.updateByPrimaryKeySelective(requestTopic);
        return requestTopic;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Topic topic) {
        topic.setUpdateTime(new Date());
        topic.setDeleted(true);
        int topicCode = topicMapper.updateByPrimaryKeySelective(topic);
        if(topicCode == 0){
            return 500;
        }
        return 200;
    }
}
