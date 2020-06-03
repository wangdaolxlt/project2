package com.lxlt.service.wxtopicservice;

import com.lxlt.bean.Topic;
import com.lxlt.bean.WxBaseQueryBean;

import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxtopicservice
 * @ClassName: WxTopicService
 * @Author: Li Haiquan
 * Date: 2020/6/3 11:32
 * project name: project2
 */
public interface WxTopicService {
    Map wxQueryTopic(WxBaseQueryBean queryBean);

    Map wxTopicDetail(int id);

    List<Topic> wxTopicRelated(int id);
}
