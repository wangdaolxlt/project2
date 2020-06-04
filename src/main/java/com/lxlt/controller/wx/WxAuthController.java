package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.LoginBean;
import com.lxlt.bean.User;
import com.lxlt.bean.userbean.WxUserInfo;
import com.lxlt.service.wxauthservice.WxAuthService;
import com.lxlt.shiro.token.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxAuthController
 * @Author: Li Haiquan
 * Date: 2020/6/1 18:23
 * project name: project2
 */
@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    WxAuthService wxAuthService;

    @RequestMapping("login")
    public BaseRespVo wxUserLogin(@RequestBody LoginBean loginBean){
/*        BaseRespVo baseRespVo = new BaseRespVo();
        WxUserInfo wxUserInfo = new WxUserInfo();
        wxUserInfo.setNickname("test1");
        wxUserInfo.setAvatarUrl("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
        Map map = new HashMap();
        map.put("userInfo", wxUserInfo);
        map.put("tokenExpire", "2020-06-02T18:29:06.326");
        map.put("token", "myvbdkiikpciuwmrrrq6kwi65eavn4wy");

        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        return baseRespVo;*/
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        //type作为分发的识别标志
        MallToken wxToken = new MallToken(username, password, "wx");
        Subject subject = SecurityUtils.getSubject();
        BaseRespVo baseRespVo = new BaseRespVo();
        try {
            subject.login(wxToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("失败");
            return baseRespVo;
        }

        HashMap<String, Object> userInfo = wxAuthService.getUserInfo(username);
        baseRespVo.setData(userInfo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");
        return resp;
    }

}
