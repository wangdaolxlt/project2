package com.lxlt.mapper;

import com.lxlt.bean.User;
import com.lxlt.bean.UserExample;
import com.lxlt.bean.statbean.StatUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    List<StatUser> selectUsers();

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectUserById(@Param("id") Integer id);

    /**
     * 根据用户名查询密码
     * @param username 用户名
     * @return
     */
    String getPasswordByUsername(@Param("username") String username);
}
