package com.lxlt.controller;
import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Role;
import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.bean.rolebean.RoleQueryBean;
import com.lxlt.service.roleservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public BaseRespVo options() {
        BaseRespVo baseRespVo = new BaseRespVo();
        List<RoleOptionsData> roleOptionsDataList = roleService.queryOptions();
        baseRespVo.setData(roleOptionsDataList);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("list")
    public BaseRespVo list (RoleQueryBean roleQueryBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map roleMap = roleService.queryRole(roleQueryBean);
        if (roleMap == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(roleMap);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create (@RequestBody Role requestRole){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map = roleService.createRole(requestRole);
        int code = (int) map.get("code");
        if(code == 640){
            baseRespVo.setErrmsg("角色已存在");
            baseRespVo.setErrno(640);
            return baseRespVo;
        }
        if (code == 502) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        Role role = (Role) map.get("role");
        baseRespVo.setErrno(0);
        baseRespVo.setData(role);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update (@RequestBody Role role){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = roleService.updateRole(role);
        if(code == 640){
            baseRespVo.setErrno(640);
            baseRespVo.setErrmsg("角色已存在");
            return baseRespVo;
        }
        if (code == 502) {
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete (@RequestBody Role role){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = roleService.deleteById(role);
        if (code == 500) {
            baseRespVo.setErrno(642);
            baseRespVo.setErrmsg("当前角色存在管理员，不能删除");
            return baseRespVo;
        }
        if (code == 501) {
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
