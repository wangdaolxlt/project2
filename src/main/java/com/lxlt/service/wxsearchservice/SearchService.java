package com.lxlt.service.wxsearchservice;

import com.lxlt.bean.SearchHistory;
import com.lxlt.bean.keywordbean.Keyword;

import java.util.List;
import java.util.Map;

public interface SearchService {

    Map<String,Object> queryIndex();

    List<String> searchHelper(String keyword);

    Integer clearHistory();

    void insertHistory(String keyword);
}

