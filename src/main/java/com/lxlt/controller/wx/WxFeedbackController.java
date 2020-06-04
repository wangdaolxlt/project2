package com.lxlt.controller.wx;

import com.lxlt.bean.Feedback;
import com.lxlt.bean.Result;
import com.lxlt.bean.User;
import com.lxlt.bean.UserExample;
import com.lxlt.mapper.UserMapper;
import com.lxlt.service.feedbackservice.FeedbackService;
import com.lxlt.service.wxfeedbackservice.WxFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("wx/feedback")
public class WxFeedbackController {
    @Autowired
    WxFeedbackService wxfeedbackService;

    @RequestMapping("submit")
    public Result wxFeedbackSubmit(@RequestBody Feedback feedback){
        Result result = new Result();
        Integer integer = wxfeedbackService.insertFeedback(feedback);
        if(integer == 1) {
            result.setErrno(0);
            result.setErrmsg("成功");
        }else {
            result.setErrno(502);
            result.setErrmsg("请登录");
        }
        return result;
    }
}
