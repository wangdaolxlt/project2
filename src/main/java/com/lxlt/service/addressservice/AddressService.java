package com.lxlt.service.addressservice;

import com.lxlt.bean.addressbean.*;

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

    String queryProvinceById(Integer provinceId);

    String queryCityById(Integer cityId);

    String queryAreaById(Integer areaId);


    void updateWxAddress(Address address);

    Integer insertWxAddress(Address address);

    Integer deleteWxAddressById(Integer id);
}
