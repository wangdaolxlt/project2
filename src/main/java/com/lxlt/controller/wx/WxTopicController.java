package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Topic;
import com.lxlt.bean.WxBaseQueryBean;
import com.lxlt.service.wxtopicservice.WxTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxTopicController
 * @Author: Li Haiquan
 * Date: 2020/6/3 11:29
 * project name: project2
 */
@RestController
@RequestMapping("wx/topic")
public class WxTopicController {
    @Autowired
    WxTopicService wxTopicService;

    /**
     * 查询list
     * @param queryBean
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo wxTopicList(WxBaseQueryBean queryBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map = wxTopicService.wxQueryTopic(queryBean);
        if(map == null){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("detail")
    public BaseRespVo wxTopicDetail(Topic topic){
        BaseRespVo baseRespVo = new BaseRespVo();
        int id = topic.getId();
        Map map = wxTopicService.wxTopicDetail(id);
        if(map == null){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("related")
    public BaseRespVo wxTopicRelated(Topic topic){
        BaseRespVo baseRespVo = new BaseRespVo();
        int id = topic.getId();
        List<Topic> list = wxTopicService.wxTopicRelated(id);
        baseRespVo.setErrno(0);
        baseRespVo.setData(list);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
