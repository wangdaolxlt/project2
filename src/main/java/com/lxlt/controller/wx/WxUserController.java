package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.userservice.UserService;
import com.lxlt.service.wxuserservice.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: UserController
 * @Author: admin
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("wx/user")
public class WxUserController {

    @Autowired
    WxUserService wxUserService;

    @RequestMapping("index")
    public BaseRespVo list() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> orders = wxUserService.queryAllOrders();
        baseRespVo.setData(orders);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
