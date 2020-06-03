package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.WxBaseQueryBean;
import com.lxlt.service.wxtopicservice.WxTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseRespVo wxTopicDetail(){
        BaseRespVo baseRespVo = new BaseRespVo();

        return baseRespVo;
    }

    @RequestMapping("related")
    public BaseRespVo wxTopicRelated(){
        BaseRespVo baseRespVo = new BaseRespVo();
        return baseRespVo;
    }
}
