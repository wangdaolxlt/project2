package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.bean.couponbean.CouponReq;
import com.lxlt.bean.couponbean.CouponUser;
import com.lxlt.service.couponservice.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxCouponController
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */

@RequestMapping("wx/coupon")
@RestController
public class WxCouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("list")
    public BaseRespVo list(CouponReq couponReq) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> coupons = couponService.queryWxAllCoupons(couponReq);
        baseRespVo.setData(coupons);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("mylist")
    public BaseRespVo mylist(CouponReq couponReq) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> coupons = couponService.queryWxAllMyCoupons(couponReq);
        baseRespVo.setData(coupons);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

//    @RequestMapping("selectlist")
//    public BaseRespVo selectlist(CouponReq couponReq) {
//        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
//        HashMap<String, Object> coupons = couponService.queryWxselectCoupons(couponReq);
//        return null;
//    }

    @RequestMapping("receive")
    public BaseRespVo receive(@RequestBody CouponUser couponUser) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Boolean coupon = couponService.insertWxCoupon(couponUser);
        if (coupon) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
        } else {
            baseRespVo.setErrno(740);
            baseRespVo.setErrmsg("优惠券已经领取过");
        }
            return baseRespVo;
    }

    @RequestMapping("exchange")
    public BaseRespVo exchange(@RequestBody Coupon coupon) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String code = coupon.getCode();
        couponService.exchangeCouponByCode(code);
        return null;
    }
}
