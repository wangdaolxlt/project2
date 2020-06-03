package com.lxlt.service.wxcommentservice;

import com.lxlt.bean.commentbean.WxCommentReqVo;
import com.lxlt.bean.commentbean.WxCommentRespDataVo;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxcommentservice
 * @ClassName: WxCommentService
 * @Author: Li Haiquan
 * Date: 2020/6/4 3:19
 * project name: project2
 */
public interface WxCommentService {
    Map commentList(WxCommentReqVo wxCommentReqVo);

    WxCommentRespDataVo commentCount(WxCommentReqVo wxCommentReqVo);
}
