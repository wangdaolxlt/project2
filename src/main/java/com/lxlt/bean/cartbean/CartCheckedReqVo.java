package com.lxlt.bean.cartbean;

import lombok.Data;

import java.util.List;

/**
 * @PackgeName: com.lxlt.bean.cartbean
 * @ClassName: CartCheckedReqVo
 * @Author: Li Haiquan
 * Date: 2020/6/2 11:19
 * project name: project2
 */
@Data
public class CartCheckedReqVo {
    private Integer isChecked;
    private List<Integer> productIds;
}
