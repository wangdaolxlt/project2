package com.lxlt.service.addressservice;


import com.lxlt.bean.Address;
import com.lxlt.bean.AddressExample;
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
    public HashMap<String, Object> queryAllAddresses() {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andIdGreaterThan(0);

        int addressNum = (int) addressMapper.countByExample(addressExample);
        List<Address> addressList = addressMapper.selectByExample(addressExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", addressList);
        hashMap.put("total", addressNum);
        return hashMap;
    }
}
