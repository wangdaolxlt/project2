package com.lxlt.service.commentservice;

import com.lxlt.bean.Comment;
import com.lxlt.bean.commentbean.CommentQueryBean;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.commentservice
 * @ClassName: CommentService
 * @Author: Li Haiquan
 * Date: 2020/5/30 10:53
 * project name: project2
 */
public interface CommentService {
    Map queryComment(CommentQueryBean commentQueryBean);

    int deleteById(Comment comment);
}
