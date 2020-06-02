package com.lxlt.bean.addressbean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.addressbean
 * @ClassName: AddressReq
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */

@Data
public class AddressReq {

    private Integer page;
    private Integer limit;
    private String sort;
    private String order;
}
