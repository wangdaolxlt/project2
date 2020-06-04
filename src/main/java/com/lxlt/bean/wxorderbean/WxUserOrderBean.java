package com.lxlt.bean.wxorderbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackgeName: com.lxlt.bean.wxorderbean
 * @ClassName: WxUserOrderBean
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserOrderBean {

    Integer unpaid;
    Integer unship;
    Integer unrecv;
    Integer uncomment;

}
