package com.lxlt.service.topicservice;

import com.lxlt.bean.Topic;
import com.lxlt.bean.topicbean.TopicQueryBean;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.topicservice
 * @ClassName: TopicService
 * @Author: Li Haiquan
 * Date: 2020/5/30 15:19
 * project name: project2
 */
public interface TopicService {
    Map queryTopic(TopicQueryBean topicQueryBean);

    Topic createTopic(Topic requestTopic);

    Topic updateTopic(Topic requestTopic);

    int deleteById(Topic topic);
}
