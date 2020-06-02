package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.addressbean.AddressReq;
import com.lxlt.service.addressservice.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: UserController
 * @Author: admin
 * Date: 2020/5/29 14:28
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("admin/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("list")
    public BaseRespVo list(AddressReq addressReq) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> hashMap = addressService.queryAllAddresses(addressReq);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
