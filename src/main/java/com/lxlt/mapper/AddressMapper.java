package com.lxlt.mapper;

import com.lxlt.bean.addressbean.Address;
import com.lxlt.bean.AddressExample;
import com.lxlt.bean.addressbean.WxAddressDetailBean;
import com.lxlt.bean.addressbean.WxAddressListBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<WxAddressListBean> selectWxAddressList();

    WxAddressDetailBean selectWxDetailAddress(Integer id);

    Integer deleteAddressById(Integer id);
}
