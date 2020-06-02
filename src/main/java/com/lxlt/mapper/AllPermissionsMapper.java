package com.lxlt.mapper;

import com.lxlt.bean.AllPermissions;
import com.lxlt.bean.AllPermissionsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllPermissionsMapper {
    long countByExample(AllPermissionsExample example);

    int deleteByExample(AllPermissionsExample example);

    int deleteByPrimaryKey(Integer primaryId);

    int insert(AllPermissions record);

    int insertSelective(AllPermissions record);

    List<AllPermissions> selectByExample(AllPermissionsExample example);

    AllPermissions selectByPrimaryKey(Integer primaryId);

    int updateByExampleSelective(@Param("record") AllPermissions record, @Param("example") AllPermissionsExample example);

    int updateByExample(@Param("record") AllPermissions record, @Param("example") AllPermissionsExample example);

    int updateByPrimaryKeySelective(AllPermissions record);

    int updateByPrimaryKey(AllPermissions record);
}