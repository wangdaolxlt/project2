package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Topic;
import com.lxlt.bean.topicbean.TopicQueryBean;
import com.lxlt.service.topicservice.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: TopicController
 * @Author: Li Haiquan
 * Date: 2020/5/30 15:18
 * project name: project2
 */
@RestController
@RequestMapping("admin/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping("list")
    public BaseRespVo list(TopicQueryBean topicQueryBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map topicMap = topicService.queryTopic(topicQueryBean);
        if(topicMap == null){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(topicMap);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody Topic requestTopic){
        BaseRespVo baseRespVo = new BaseRespVo();
        Topic topic = topicService.createTopic(requestTopic);
        if(topic == null){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(topic);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Topic requestTopic){
        BaseRespVo baseRespVo = new BaseRespVo();
        Topic updateTopic = topicService.updateTopic(requestTopic);
        if(updateTopic == null){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(updateTopic);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Topic topic){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = topicService.deleteById(topic);
        if(code != 200){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
