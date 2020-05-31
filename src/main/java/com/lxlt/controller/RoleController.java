package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
<<<<<<< HEAD
import com.lxlt.bean.rolebean.RoleOptions;
import com.lxlt.service.rollservice.RoleService;
=======
import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.service.roleservice.RoleService;
>>>>>>> 4f9631b51957df43fa0e6cdf17f0374c4cbb9d38
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

<<<<<<< HEAD
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
=======
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

>>>>>>> 4f9631b51957df43fa0e6cdf17f0374c4cbb9d38
    }
}
