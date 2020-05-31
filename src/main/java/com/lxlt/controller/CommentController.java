package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Comment;
import com.lxlt.bean.commentbean.CommentQueryBean;
import com.lxlt.service.commentservice.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: CommentController
 * @Author: Li Haiquan
 * Date: 2020/5/30 10:27
 * project name: project2
 */
@RestController
@RequestMapping("admin/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("list")
    public BaseRespVo list(CommentQueryBean commentQueryBean){
        BaseRespVo baseRespVo = new BaseRespVo();

        Map commentMap = commentService.queryComment(commentQueryBean);
        if(commentMap == null){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(commentMap);
        return baseRespVo;
    }


    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Comment comment){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = commentService.deleteById(comment);
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
