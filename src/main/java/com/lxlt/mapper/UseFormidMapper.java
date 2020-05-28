package com.lxlt.mapper;

import com.lxlt.bean.UseFormid;
import com.lxlt.bean.UseFormidExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UseFormidMapper {
    long countByExample(UseFormidExample example);

    int deleteByExample(UseFormidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UseFormid record);

    int insertSelective(UseFormid record);

    List<UseFormid> selectByExample(UseFormidExample example);

    UseFormid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UseFormid record, @Param("example") UseFormidExample example);

    int updateByExample(@Param("record") UseFormid record, @Param("example") UseFormidExample example);

    int updateByPrimaryKeySelective(UseFormid record);

    int updateByPrimaryKey(UseFormid record);
}
