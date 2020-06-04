package com.lxlt.service.wxauthservice;

import com.lxlt.bean.User;
import com.lxlt.bean.UserExample;
import com.lxlt.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service.wxauthservice
 * @ClassName: WxAuthServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/4 10:09
 * project name: project2
 */
@Service
public class WxAuthServiceImpl implements WxAuthService{
    @Autowired
    UserMapper userMapper;
    @Override
    public HashMap<String, Object> getUserInfo(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        User user = userList.size() > 0 ? userList.get(0) : null;
        HashMap<String, Object> map = new HashMap<>();
        // userInfo参数
        HashMap<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("nickName",user.getNickname());
        userInfoMap.put("avatarUrl",user.getAvatar());
        map.put("userInfo",userInfoMap);
        // token
        map.put("tokenExpire",new Date());
        Subject subject = SecurityUtils.getSubject();
        String token = (String)subject.getSession().getId();
        map.put("token",token);

        return map;
    }
}
