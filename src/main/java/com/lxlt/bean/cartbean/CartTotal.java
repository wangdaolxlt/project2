package com.lxlt.bean.cartbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @PackgeName: com.lxlt.bean.cartbean
 * @ClassName: CartTotal
 * @Author: Li Haiquan
 * Date: 2020/6/2 10:35
 * project name: project2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartTotal {
    private int goodsCount;
    private BigDecimal goodsAmount;
    private int checkedGoodsCount;
    private BigDecimal checkedGoodsAmount;
}
