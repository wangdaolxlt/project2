package com.lxlt.service.addressservice;


import com.github.pagehelper.PageHelper;
import com.lxlt.bean.addressbean.*;
import com.lxlt.bean.AddressExample;
import com.lxlt.mapper.AddressMapper;
import com.lxlt.mapper.RegionMapper;
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

    @Autowired
    RegionMapper regionMapper;

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

    @Override
    public String queryProvinceById(Integer provinceId) {
        return regionMapper.selectByPrimaryKey(provinceId).getName();
    }

    @Override
    public String queryCityById(Integer cityId) {
        return regionMapper.selectByPrimaryKey(cityId).getName();
    }

    @Override
    public String queryAreaById(Integer areaId) {
        return regionMapper.selectByPrimaryKey(areaId).getName();
    }

    @Override
    public void updateWxAddress(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public Integer insertWxAddress(Address address) {
        return addressMapper.insertSelective(address);
    }

    @Override
    public Integer deleteWxAddressById(Integer id) {
        return addressMapper.deleteAddressById(id);
    }


}
