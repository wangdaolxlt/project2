package com.lxlt.service.userservice;

import com.lxlt.bean.OrderExample;
import com.lxlt.bean.User;
import com.lxlt.bean.UserExample;
import com.lxlt.mapper.OrderMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: UserServiceImpl
 * @Author: admin
 * Date: 2020/5/29 11:45
 * project name: project2
 * @Version:
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public HashMap<String, Object> queryAllUsers() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdGreaterThan(0);

        int usersNum = (int) userMapper.countByExample(userExample);
        List<User> userList = userMapper.selectByExample(userExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", userList);
        hashMap.put("total", usersNum);
        return hashMap;
    }

}
