package com.lxlt.controller;

import com.lxlt.bean.adminbean.AdminReq;
import com.lxlt.bean.adminbean.AdminRespVo;
import com.lxlt.service.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createAdmin(){

    }

    //更新
    @RequestMapping("update")
    public void updateAdmin(){

    }

    //删除
    @RequestMapping("delete")
    public void deleteAdmin(){

    }
}
