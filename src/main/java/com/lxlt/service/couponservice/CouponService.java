package com.lxlt.service.couponservice;

import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.bean.couponbean.CouponReq;
import com.lxlt.bean.couponbean.CouponUser;
import com.lxlt.bean.couponbean.QueryCouponBean;

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


    HashMap<String, Object> queryCoupons(QueryCouponBean queryCouponBean);

    Coupon addCoupon(Coupon coupon);

    List<Coupon> readCoupon(int id);

    Coupon updateCoupon(Coupon coupon);

    Integer deleteCoupon(Coupon coupon);

    HashMap<String, Object> listuserCoupon();

    HashMap<String, Object> queryWxAllCoupons(CouponReq couponReq);

    HashMap<String, Object> queryWxAllMyCoupons(CouponReq couponReq);

    HashMap<String, Object> queryWxselectCoupons(CouponReq couponReq);

    Boolean insertWxCoupon(CouponUser couponUser);

    void exchangeCouponByCode(String code);
}
