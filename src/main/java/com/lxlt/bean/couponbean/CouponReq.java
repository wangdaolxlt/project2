package com.lxlt.bean.couponbean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.couponbean
 * @ClassName: CouponReq
 * @Author: Pipboy
 * Date: 2020/6/3 17:38
 * project name: project2
 * @Version:
 * @Description:
 */
@Data
public class CouponReq {
    Integer page;
    Integer size;
    Short status;
    Integer cartId;
    Integer grouponRulesId;
}
