package com.lxlt.controller;

import com.lxlt.bean.Admin;
import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.adminbean.AdminReq;
import com.lxlt.bean.adminbean.AdminRespVo;
import com.lxlt.service.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    //分页查询
    @RequestMapping("list")
    public AdminRespVo listAdmin(AdminReq adminReq){
        AdminRespVo<Map<String,Object>> adminRespVo = new AdminRespVo();
        Map<String, Object> map = adminService.queryAdmin(adminReq);
        adminRespVo.setErrno(0);
        adminRespVo.setErrmsg("成功");
        adminRespVo.setData(map);
        return adminRespVo;
    }

    //添加
    @RequestMapping("create")
    public BaseRespVo createAdmin(@RequestBody Admin requestAdmin){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map = adminService.insertAdmin(requestAdmin);
        int code = (int) map.get("code");
        if(code == 601){
            //管理员名称不符合规定
            baseRespVo.setErrno(601);
            baseRespVo.setErrmsg("管理员名称不符合规定");
            return baseRespVo;
        }
        if(code == 602){
            baseRespVo.setErrno(602);
            baseRespVo.setErrmsg("管理员已存在");
            return baseRespVo;
        }
        if(code == 603){
            baseRespVo.setErrno(603);
            baseRespVo.setErrmsg("密码格式不规范");
            return baseRespVo;
        }
        if(code == 501){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器异常");
            return baseRespVo;
        }
        Admin admin = (Admin) map.get("admin");
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(admin);
        return baseRespVo;
    }

    //更新
    @RequestMapping("update")
    public BaseRespVo updateAdmin(@RequestBody Admin requestAdmin){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map = adminService.updateAdmin(requestAdmin);
        int code = (int) map.get("code");
        if(code == 601){
            //管理员名称不符合规定
            baseRespVo.setErrno(601);
            baseRespVo.setErrmsg("管理员名称不符合规定");
            return baseRespVo;
        }
        if(code == 602){
            baseRespVo.setErrno(602);
            baseRespVo.setErrmsg("管理员已存在");
            return baseRespVo;
        }
        if(code == 603){
            baseRespVo.setErrno(603);
            baseRespVo.setErrmsg("密码格式不规范");
            return baseRespVo;
        }
        if(code == 501){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器异常");
            return baseRespVo;
        }
        Admin admin = (Admin) map.get("admin");
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(admin);
        return baseRespVo;

    }

    //删除
    @RequestMapping("delete")
    public BaseRespVo deleteAdmin(@RequestBody Admin admin){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = adminService.deleteAdmin(admin);
        if(code != 200){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
