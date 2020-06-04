package com.lxlt.bean.couponbean;

import com.lxlt.bean.BaseQueryBean;
import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/4 7:42
 */
@Data
public class QueryCouponBean extends BaseQueryBean {
    String name;
    Short type;
    Short status;

}
