package com.lxlt.service.wxfeedbackservice;

import com.lxlt.bean.Feedback;
import com.lxlt.bean.Storage;
import com.lxlt.bean.StorageExample;
import com.lxlt.mapper.FeedbackMapper;
import com.lxlt.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WxFeedbackServiceImpl implements WxFeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    StorageMapper storageMapper;
    @Override
    public Integer insertFeedback(Feedback feedback) {
        Integer i = null;
        feedback.setDeleted(false);
        feedback.setAddTime(new Date());
        feedback.setUpdateTime(new Date());
        //这里的用户名和id都是写死的
        feedback.setUserId(1);
        feedback.setUsername("test1");
        feedback.setPicUrls(feedback.getPicUrls());
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
