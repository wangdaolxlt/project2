package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.LoginBean;
import com.lxlt.service.authservice.AuthService;
import com.lxlt.shiro.token.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 16:13
 */

@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * 管理员登陆
     * @param loginBean
     * @return
     */
    @RequestMapping("login")
    public BaseRespVo login(@RequestBody LoginBean loginBean) {
/*        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData("6cfc3d7c-bf85-4886-811f-0e3ea550c72a");
        baseRespVo.setErrmsg("成功");
        return baseRespVo;*/
        MallToken token = new MallToken(loginBean.getUsername(), loginBean.getPassword(), "admin");
        // 获得主体
        Subject subject = SecurityUtils.getSubject();
        BaseRespVo baseRespVo = new BaseRespVo();
        String username = null;
        try{
            subject.login(token);
            username = (String) subject.getPrincipal();
        }catch (AuthenticationException e){
            e.printStackTrace();
            // 创建登录失败时的日志
            authService.createFailAdminLog(username);
            baseRespVo.setErrno(1);
            baseRespVo.setErrmsg("失败");
            return baseRespVo;
        }
        String sessionId = (String) subject.getSession().getId();
        baseRespVo.setData(sessionId);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        // 增加登陆成功的log
        authService.createSuccessfulAdminLog(username);
        return baseRespVo;
    }

    /**
     * 管理员信息
     * @return
     */
    @RequestMapping("info")
    public BaseRespVo info() {
        //return "{\"errno\":0,\"data\":{\"roles\":[\"超级管理员\"],\"name\":\"admin123\",\"perms\":[\"*\"],\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"},\"errmsg\":\"成功\"}";

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        BaseRespVo baseRespVo = new BaseRespVo();
        try {
            // 根据用户名去查询他的角色权限等信息
            Map map = authService.getInfo(username);
            baseRespVo.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("系统内部错误");
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        subject.logout();
        BaseRespVo respVo = new BaseRespVo();
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        authService.createAdminLogoutLog(username);
        return respVo;
    }
}
