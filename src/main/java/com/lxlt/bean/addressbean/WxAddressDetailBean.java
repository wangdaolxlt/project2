package com.lxlt.bean.addressbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackgeName: com.lxlt.bean.addressbean
 * @ClassName: WxAddressListBean
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxAddressDetailBean {


    private Boolean isDefault;
    private Integer areaId;
    private String address;
    private String cityName;
    private String areaName;
    private String name;
    private String mobile;
    private Integer id;
    private Integer cityId;
    private String provinceName;
    private Integer provinceId;

}
