package com.lxlt.bean.cartbean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.cartbean
 * @ClassName: CartCheckoutReqVo
 * @Author: Li Haiquan
 * Date: 2020/6/3 20:30
 * project name: project2
 */
@Data
public class CartCheckoutReqVo {
    private Integer cartId;
    private Integer addressId;
    private Integer couponId;
    private Integer grouponRulesId;
}
