package com.lxlt.bean.wxsearchbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class SearchResp {
    Integer id;
    String keyword;
    String url;
    boolean isHot;
    boolean isDefault;
    Integer sortOrder;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date updateTime;
    boolean deleted;
}
