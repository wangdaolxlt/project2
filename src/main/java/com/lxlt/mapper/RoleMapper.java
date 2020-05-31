package com.lxlt.mapper;

import com.lxlt.bean.rolebean.Role;
import com.lxlt.bean.rolebean.RoleExample;
import com.lxlt.bean.rolebean.RoleOptions;
import com.lxlt.bean.statbean.StatUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    List<RoleOptions> selectLabel();

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
