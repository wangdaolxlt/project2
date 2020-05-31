package com.lxlt.bean.topicbean;

import com.lxlt.bean.BaseQueryBean;
import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.topicbean
 * @ClassName: TopicQueryBean
 * @Author: Li Haiquan
 * Date: 2020/5/30 15:23
 * project name: project2
 */
@Data
public class TopicQueryBean extends BaseQueryBean {
    /**
     * 专题标题
     */
    private String title;

    /**
     * 专题子标题
     */
    private String subtitle;
}
