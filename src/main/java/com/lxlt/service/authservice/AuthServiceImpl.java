package com.lxlt.service.authservice;

import com.lxlt.bean.Admin;
import com.lxlt.bean.AdminExample;
import com.lxlt.bean.RoleExample;
import com.lxlt.bean.logbean.Log;
import com.lxlt.mapper.AdminMapper;
import com.lxlt.mapper.LogMapper;
import com.lxlt.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @PackgeName: com.lxlt.service.authservice
 * @ClassName: AuthServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/4 9:50
 * project name: project2
 */
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    HttpServletRequest request;
    @Autowired
    LogMapper logMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AdminMapper adminMapper;

    @Override
    public void createFailAdminLog(String username) {
        Log log = new Log();
        log.setAdmin(username);
        //获取登录的IP
        String ip= request.getRemoteAddr();
        log.setIp(ip);
        log.setType(1);
        log.setStatus(true);
        log.setAction("退出");
        log.setResult("退出成功");
        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setDeleted(false);
        logMapper.insertSelective(log);
    }

    @Override
    public void createSuccessfulAdminLog(String username) {
        Log log = new Log();
        log.setAdmin(username);
        //获取登录的IP
        String ip= request.getRemoteAddr();
        log.setIp(ip);
        log.setType(1);
        log.setStatus(true);
        log.setAction("登陆");
        log.setResult("登陆成功");
        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setDeleted(false);
        logMapper.insertSelective(log);
    }

    /**
     * 获取信息
     * @param username
     * @return
     */
    @Override
    public Map getInfo(String username) {
        Admin admin = queryAdminByUsername(username);

        HashMap<String, Object> map = new HashMap<>();
        map.put("roles","");
        map.put("name",username);
        map.put("perms","");
        map.put("avatar",admin.getAvatar());
        return map;
    }

    @Override
    public void createAdminLogoutLog(String username) {
        Log log = new Log();
        log.setAdmin(username);
        //获取登录的IP
        String ip= request.getRemoteAddr();
        log.setIp(ip);
        log.setType(1);
        log.setStatus(true);
        log.setAction("退出");
        log.setResult("退出成功");
        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setDeleted(false);
        logMapper.insertSelective(log);
    }

    private Admin queryAdminByUsername(String username) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 若查到则返回（list中只有一个） 否则返回空
        Admin admin = admins.size() > 0 ? admins.get(0) : null;
        return admin;
    }
}
