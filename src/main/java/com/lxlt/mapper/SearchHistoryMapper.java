package com.lxlt.mapper;

import com.lxlt.bean.SearchHistory;
import com.lxlt.bean.SearchHistoryExample;
import com.lxlt.bean.keywordbean.Keyword;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SearchHistoryMapper {
    long countByExample(SearchHistoryExample example);

    int deleteByExample(SearchHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SearchHistory record);

    int insertSelective(SearchHistory record);

    List<SearchHistory> selectByExample(SearchHistoryExample example);

    SearchHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SearchHistory record, @Param("example") SearchHistoryExample example);

    int updateByExample(@Param("record") SearchHistory record, @Param("example") SearchHistoryExample example);

    int updateByPrimaryKeySelective(SearchHistory record);

    int updateByPrimaryKey(SearchHistory record);

    List<Keyword> selectByKeyword(SearchHistoryExample searchHistoryExample);
}
