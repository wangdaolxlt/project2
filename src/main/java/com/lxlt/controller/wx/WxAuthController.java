package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.User;
import com.lxlt.bean.userbean.WxUserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("login")
    public BaseRespVo wxUserLogin(@RequestBody User user){
        BaseRespVo baseRespVo = new BaseRespVo();
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
        return baseRespVo;
    }
}
