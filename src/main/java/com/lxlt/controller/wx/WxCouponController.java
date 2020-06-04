package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.BaseRespVo2;
import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.bean.couponbean.CouponReq;
import com.lxlt.bean.couponbean.CouponUser;
import com.lxlt.service.couponservice.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @RequestMapping("selectlist")
    public BaseRespVo selectlist(CouponReq couponReq) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        BigDecimal price = couponService.queryWxGoodsPriceById(couponReq.getCartId());
        List<Coupon> coupons = couponService.queryWxEnableCouponList(price);
        return null;
    }

    @RequestMapping("receive")
    public BaseRespVo2 receive(@RequestBody CouponUser couponUser) {
        BaseRespVo2 baseRespVo = new BaseRespVo2();
        Coupon coupon = couponService.queryCouponById(couponUser.getCouponId());
        String nowTime = null;
        String endTime = null;

        if (coupon.getTimeType() == 0) {
            coupon.setStartTime(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, coupon.getDays());
            coupon.setEndTime(calendar.getTime());
        } else{
            //验证过期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            nowTime = sdf.format(new Date());
            endTime = sdf.format(coupon.getEndTime());
            couponUser.setStartTime(coupon.getStartTime());
            couponUser.setEndTime(coupon.getEndTime());
        }
        if (coupon.getTotal() == 0){
            baseRespVo.setErrno(740);
            baseRespVo.setErrmsg("优惠券已领完");
        }else if(coupon.getTimeType() == 1 && nowTime.compareTo(endTime) > 0){
            baseRespVo.setErrno(740);
            baseRespVo.setErrmsg("优惠券已过期");
        }else{
            couponUser.setUserId(4);
            coupon.setTotal(coupon.getTotal() - 1);
            couponService.updateCoupon(coupon);
            couponService.insertWxCoupon(couponUser);
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
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
