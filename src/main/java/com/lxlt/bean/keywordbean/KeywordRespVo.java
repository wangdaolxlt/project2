package com.lxlt.bean.keywordbean;

import lombok.Data;

@Data
public class KeywordRespVo<T> {
        private Integer errno;
        private T data;
        private String errmsg;
}
