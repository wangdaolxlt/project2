package com.lxlt.service.feedbackservice;

import com.lxlt.bean.Feedback;
import com.lxlt.bean.FeedbackExample;
import com.lxlt.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: FeedbackServiceImpl
 * @Author: admin
 * Date: 2020/5/29 17:16
 * project name: project2
 * @Version:
 * @Description:
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public HashMap<String, Object> queryAllFeedback() {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.createCriteria().andIdGreaterThan(0);

        int feedbackNum = (int) feedbackMapper.countByExample(feedbackExample);
        List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("items", feedbackList);
        hashMap.put("total", feedbackNum);
        return null;
    }
}
