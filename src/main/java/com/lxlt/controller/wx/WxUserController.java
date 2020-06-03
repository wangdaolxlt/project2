package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: UserController
 * @Author: admin
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("admin/user")
public class WxUserController {

    @Autowired
    UserService userService;

    @RequestMapping("index")
    public BaseRespVo list() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<String> orders = userService.queryAllOrders();
        baseRespVo.setData(orders);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
