package com.lxlt.service.adminservice;

import com.lxlt.bean.Admin;
import com.lxlt.bean.adminbean.AdminReq;

import java.util.Map;

public interface AdminService {
    //分页+查询
    Map<String,Object> queryAdmin(AdminReq adminReq);
    //添加
    Map insertAdmin(Admin admin);
    //更新
    Map updateAdmin(Admin admin);
    //删除
    Integer deleteAdmin(Admin admin);
}
