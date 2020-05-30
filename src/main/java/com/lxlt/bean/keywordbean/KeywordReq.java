package com.lxlt.bean.keywordbean;

import lombok.Data;

@Data
public class KeywordReq {
    Integer page;
    Integer limit;
    String keyword;
    String url;
    String sort;
    String order;
}
