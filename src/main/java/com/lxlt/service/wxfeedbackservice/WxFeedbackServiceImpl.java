package com.lxlt.service.wxfeedbackservice;

import com.lxlt.bean.Feedback;
import com.lxlt.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxFeedbackServiceImpl implements WxFeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public Integer insertFeedback(Feedback feedback) {
        Integer i = null;
        //通过正则验证就进行插入
        if(checkCellphone(feedback.getMobile())) {
            i = feedbackMapper.insertSelective(feedback);
        }
        return i;
    }
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        return cellphone.matches(regex);
    }
}
