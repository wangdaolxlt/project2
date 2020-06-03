package com.lxlt.bean.cartbean;

import com.lxlt.bean.Cart;
import com.lxlt.bean.addressbean.Address;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @PackgeName: com.lxlt.bean.cartbean
 * @ClassName: CartCheckoutRespVo
 * @Author: Li Haiquan
 * Date: 2020/6/3 20:48
 * project name: project2
 */
@Data
public class CartCheckoutRespVo {
    Integer grouponPrice;
    Integer grouponRulesId;
    Address checkedAddress;
    Double actualPrice;
    Double orderTotalPrice;
    Double couponPrice;
    Short availableCouponLength;
    Integer couponId;
    Double freightPrice;
    List<Cart> checkedGoodsList;
    BigDecimal goodsTotalPrice;
    Integer  addressId;
}
