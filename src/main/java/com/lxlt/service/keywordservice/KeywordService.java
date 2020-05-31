package com.lxlt.service.keywordservice;


import com.lxlt.bean.keywordbean.Keyword;
import com.lxlt.bean.keywordbean.KeywordReq;

import java.util.Map;

public interface KeywordService {
    //分页查询关键词
    Map<String,Object> queryKeyword(KeywordReq keywordReq);
    //添加
    Integer insertKeyword(Keyword keyword);
    //编辑
    Integer updateKeyword(Keyword keyword);
    //删除
    Integer deleteKeyword(Keyword keyword);
}
