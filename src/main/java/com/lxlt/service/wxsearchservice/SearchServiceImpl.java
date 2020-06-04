package com.lxlt.service.wxsearchservice;

import com.lxlt.bean.SearchHistory;
import com.lxlt.bean.SearchHistoryExample;
import com.lxlt.bean.keywordbean.Keyword;
import com.lxlt.bean.keywordbean.KeywordExample;
import com.lxlt.mapper.KeywordMapper;
import com.lxlt.mapper.SearchHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Override
    public Map<String, Object> queryIndex() {
        HashMap<String, Object> map = new HashMap<>();
        KeywordExample defaultKeyword = new KeywordExample();
        KeywordExample hotKeywordList = new KeywordExample();
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        //默认搜索关键字
        KeywordExample.Criteria criteria = defaultKeyword.createCriteria();
        criteria.andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        List<Keyword> defaultKeywords = keywordMapper.selectByExample(defaultKeyword);
        map.put("defaultKeyword",defaultKeywords);
        //热门搜索
        KeywordExample.Criteria criteria1 = hotKeywordList.createCriteria();
        criteria1.andIsHotEqualTo(true).andDeletedEqualTo(false);
        List<Keyword> hotKeywordLists = keywordMapper.selectByExample(hotKeywordList);
        map.put("hotKeywordList",hotKeywordLists);
        //历史记录
        SearchHistoryExample.Criteria criteria2 = searchHistoryExample.createCriteria();
        SearchHistory searchHistory = new SearchHistory();
        //按照搜索添加的日期排序
        searchHistoryExample.setOrderByClause(searchHistory.getAddTime() + " ");
        criteria2.andDeletedEqualTo(false);
        List<Keyword> historyKeywordLists = searchHistoryMapper.selectByKeyword(searchHistoryExample);
        map.put("historyKeywordList",historyKeywordLists);
        System.out.println(map);
        return map;
    }

    @Override
    public List<String> searchHelper(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andKeywordLike("%" + keyword + "%").andIsHotEqualTo(true).andDeletedEqualTo(false);
        List<String> keywords = keywordMapper.selectByKeyword(keywordExample);
        return keywords;
    }

    //查询之后要添加到历史记录
    @Override
    public void insertHistory(String keyword) {
        SearchHistory searchHistory = new SearchHistory();
        Integer userId = 1;
        searchHistory.setKeyword(keyword);
        searchHistory.setUserId(userId);
        searchHistory.setAddTime(new Date());
        searchHistory.setUpdateTime(new Date());
        searchHistory.setDeleted(false);
        searchHistory.setFrom("wx");
        searchHistoryMapper.insert(searchHistory);
    }

    @Override
    public Integer clearHistory() {
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();
        criteria.andKeywordIsNotNull();
        Integer i = searchHistoryMapper.deleteByExample(searchHistoryExample);
        return i;
    }
}
