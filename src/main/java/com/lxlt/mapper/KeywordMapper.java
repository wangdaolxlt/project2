package com.lxlt.mapper;

import com.lxlt.bean.keywordbean.Keyword;
import com.lxlt.bean.keywordbean.KeywordExample;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordMapper {
    long countByExample(KeywordExample example);

    int deleteByExample(KeywordExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Keyword record);

    int insertSelective(Keyword record);

    List<Keyword> selectByExample(KeywordExample example);

    Keyword selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByExample(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

    List<String> selectByKeyword(KeywordExample keywordExample);
}
