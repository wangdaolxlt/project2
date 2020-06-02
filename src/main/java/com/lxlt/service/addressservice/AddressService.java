package com.lxlt.service.addressservice;

import com.lxlt.bean.addressbean.AddressReq;
import com.lxlt.bean.addressbean.WxAddressDetailBean;
import com.lxlt.bean.addressbean.WxAddressListBean;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: AddressService
 * @Author: admin
 * Date: 2020/5/29 14:59
 * project name: project2
 * @Version:
 * @Description:
 */
public interface AddressService {

    HashMap<String, Object> queryAllAddresses(AddressReq addressReq);

    List<WxAddressListBean> queryWxAllAddresses();

    WxAddressDetailBean queryWxDetailAddress(Integer id);
}
