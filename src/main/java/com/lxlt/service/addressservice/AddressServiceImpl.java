package com.lxlt.service.addressservice;


import com.github.pagehelper.PageHelper;
import com.lxlt.bean.addressbean.Address;
import com.lxlt.bean.AddressExample;
import com.lxlt.bean.addressbean.AddressReq;
import com.lxlt.bean.addressbean.WxAddressDetailBean;
import com.lxlt.bean.addressbean.WxAddressListBean;
import com.lxlt.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: AddressServiceImpl
 * @Author: admin
 * Date: 2020/5/29 15:02
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public HashMap<String, Object> queryAllAddresses(AddressReq addressReq) {
        PageHelper.startPage(addressReq.getPage(), addressReq.getLimit());
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andIdGreaterThan(0);

        int addressNum = (int) addressMapper.countByExample(addressExample);
        List<Address> addressList = addressMapper.selectByExample(addressExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", addressList);
        hashMap.put("total", addressNum);
        return hashMap;
    }

    @Override
    public List<WxAddressListBean> queryWxAllAddresses() {
        return addressMapper.selectWxAddressList();
    }

    @Override
    public WxAddressDetailBean queryWxDetailAddress(Integer id) {
        return addressMapper.selectWxDetailAddress(id);
    }
}
