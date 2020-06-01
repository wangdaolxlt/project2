package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
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

    @RequestMapping("login")
    public BaseRespVo login(@RequestBody Map loginMap) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData("6cfc3d7c-bf85-4886-811f-0e3ea550c72a");
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("info")
    public String info() {
        return "{\"errno\":0,\"data\":{\"roles\":[\"超级管理员\"],\"name\":\"admin123\",\"perms\":[\"*\"],\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"},\"errmsg\":\"成功\"}";

    }
}
