package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.commentbean.WxCommentReqVo;
import com.lxlt.bean.commentbean.WxCommentRespDataVo;
import com.lxlt.service.wxcommentservice.WxCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxCommentController
 * @Author: Li Haiquan
 * Date: 2020/6/4 3:15
 * project name: project2
 */
@RestController
@RequestMapping("wx/comment")
public class WxCommentController {
    @Autowired
    WxCommentService wxCommentService;

    /**
     * 显示评论
     * @param wxCommentReqVo
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo commentList(WxCommentReqVo wxCommentReqVo){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map = wxCommentService.commentList(wxCommentReqVo);
        if(map == null){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(map);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


    /**
     * 评论总数
     * @param wxCommentReqVo
     * @return
     */
    @RequestMapping("count")
    public BaseRespVo commentCount(WxCommentReqVo wxCommentReqVo){
        BaseRespVo baseRespVo = new BaseRespVo();
        WxCommentRespDataVo wxCommentRespDataVo = wxCommentService.commentCount(wxCommentReqVo);
        baseRespVo.setData(wxCommentRespDataVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
