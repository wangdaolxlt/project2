package com.lxlt.mapper;

import com.lxlt.bean.GrouponRules;
import com.lxlt.bean.GrouponRulesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRulesMapper {
    long countByExample(GrouponRulesExample example);

    int deleteByExample(GrouponRulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    /**
     * 插入一条记录到数据库, 同时将id返回给record, 即mapper设置了useGeneratedKeys
     * @param record
     * @return
     */
    int insertSelective(GrouponRules record);

    List<GrouponRules> selectByExample(GrouponRulesExample example);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByExample(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);
}
