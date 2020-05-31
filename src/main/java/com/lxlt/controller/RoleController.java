package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.service.roleservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: RoleController
 * @Author: Li Haiquan
 * Date: 2020/5/31 11:41
 * project name: project2
 */
@RestController
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("options")
    public BaseRespVo options(){
        BaseRespVo baseRespVo = new BaseRespVo();
        List<RoleOptionsData> roleOptionsDataList = roleService.queryOptions();
        baseRespVo.setData(roleOptionsDataList);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;

    }
}
