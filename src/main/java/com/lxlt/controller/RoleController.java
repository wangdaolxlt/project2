package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.rolebean.RoleOptions;
import com.lxlt.service.rollservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/role")
public class RoleController {
    @Autowired
    RoleService rollService;
    @RequestMapping("options")
    public BaseRespVo optionsRoll(){
        BaseRespVo roleRespVo = new BaseRespVo();
        List<RoleOptions> roleOptions = rollService.rollOptions();
        roleRespVo.setErrno(0);
        roleRespVo.setErrmsg("成功");
        roleRespVo.setData(roleOptions);
        return roleRespVo;
    }
}
