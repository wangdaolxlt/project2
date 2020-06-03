package com.lxlt.bean.commentbean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.commentbean
 * @ClassName: WxCommentReqVo
 * @Author: Li Haiquan
 * Date: 2020/6/4 3:23
 * project name: project2
 */
@Data
public class WxCommentReqVo {
    Byte type;
    Integer page;
    Integer size;
    Integer showType;
    Integer valueId;
}
