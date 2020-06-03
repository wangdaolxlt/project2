package com.lxlt.bean.wxfeedbackbean;

import lombok.Data;

import java.util.List;

@Data
public class FeedbackReq {
    String content;
    String feedType;
    Boolean hasPicture;
    //电话号码要进行正则验证
    String mobile;
    List<String> picUrls;
}
