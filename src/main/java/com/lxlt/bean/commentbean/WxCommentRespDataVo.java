package com.lxlt.bean.commentbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxlt.bean.userbean.WxUserInfo;
import lombok.Data;

import java.util.Date;

/**
 * @PackgeName: com.lxlt.bean.commentbean
 * @ClassName: WxCommentRespDataVo
 * @Author: Li Haiquan
 * Date: 2020/6/4 3:41
 * project name: project2
 */
@Data
public class WxCommentRespDataVo {
    private Long hasPicCount;
    private Long allCount;

    private WxUserInfo userInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addTime;
    private String[] picList;
    private String content;
}
