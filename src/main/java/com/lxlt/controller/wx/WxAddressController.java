package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.BaseRespVo2;
import com.lxlt.bean.addressbean.Address;
import com.lxlt.bean.addressbean.WxAddressDetailBean;
import com.lxlt.bean.addressbean.WxAddressListBean;
import com.lxlt.service.addressservice.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxAddressController
 * @Author: Pipboy
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
    public BaseRespVo list( ) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<WxAddressListBean> listBean = addressService.queryWxAllAddresses();
        baseRespVo.setData(listBean);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("detail")
    public BaseRespVo detail(Integer id) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        WxAddressDetailBean detailBean = addressService.queryWxDetailAddress(id);
        String province = addressService.queryProvinceById(detailBean.getProvinceId());
        String city = addressService.queryCityById(detailBean.getCityId());
        String area = addressService.queryAreaById(detailBean.getAreaId());
        detailBean.setProvinceName(province);
        detailBean.setCityName(city);
        detailBean.setAreaName(area);
        baseRespVo.setErrno(0);
        baseRespVo.setData(detailBean);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("save")
    public BaseRespVo save(@RequestBody Address address) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Integer id = address.getId();
        if (id != 0) {
            addressService.updateWxAddress(address);
        } else {
            id = addressService.insertWxAddress(address);
        }
        baseRespVo.setData(id);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo2 delete(@RequestBody Address address) {
        Integer id = address.getId();
        BaseRespVo2 baseRespVo2 = new BaseRespVo2();
        Integer res = addressService.deleteWxAddressById(id);
        if (res > -1) {
            baseRespVo2.setErrmsg("成功");
            baseRespVo2.setErrno(0);
            return baseRespVo2;
        }
        return null;
    }
}
