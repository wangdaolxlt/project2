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
public class WxAddressListBean {

        private Boolean isDefault;
        private String detailedAddress;
        private String name;
        private String mobile;
        private Integer id;
        private Integer pid;

}
