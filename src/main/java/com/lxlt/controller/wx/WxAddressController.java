package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.addressbean.WxAddressDetailBean;
import com.lxlt.bean.addressbean.WxAddressListBean;
import com.lxlt.service.addressservice.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxAddressController
 * @Author: Pipboy
 * Date: 2020/6/2 10:55
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("wx/address")
public class WxAddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("list")
    public BaseRespVo list() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<WxAddressListBean> listBean = addressService.queryWxAllAddresses();
        baseRespVo.setErrno(0);
        baseRespVo.setData(listBean);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("detail")
    public BaseRespVo detail(Integer id) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        WxAddressDetailBean detailBean = addressService.queryWxDetailAddress(id);
        baseRespVo.setErrno(0);
        baseRespVo.setData(detailBean);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
