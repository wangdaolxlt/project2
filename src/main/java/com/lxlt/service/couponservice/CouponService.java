package com.lxlt.service.couponservice;

import com.lxlt.bean.Coupon;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service.couponservice
 * @ClassName: CouponService
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */
public interface CouponService {


    HashMap<String, Object> queryAllCoupons();

    Coupon addCoupon(Coupon coupon);

    List<Coupon> readCoupon(int id);

    Coupon updateCoupon(Coupon coupon);

    Integer deleteCoupon(Coupon coupon);

    HashMap<String, Object> listuserCoupon();
}
