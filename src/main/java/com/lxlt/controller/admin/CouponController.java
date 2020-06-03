package com.lxlt.controller.admin;


import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.BaseRespVo2;
import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.service.couponservice.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: UserController
 * @Author: Pipboy
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("admin/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("list")
    public BaseRespVo list() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> hashMap = couponService.queryAllCoupons();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody Coupon coupon) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Coupon addCoupon = couponService.addCoupon(coupon);
        baseRespVo.setData(addCoupon);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("read")
    public BaseRespVo read(@RequestParam("id") int id) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<Coupon> readCoupon = couponService.readCoupon(id);
        baseRespVo.setData(readCoupon);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Coupon coupon) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Coupon couponUpdate = couponService.updateCoupon(coupon);
        baseRespVo.setData(couponUpdate);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo2 delete(@RequestBody Coupon coupon) {
        BaseRespVo2 BaseRespVo2 = new BaseRespVo2();
        Integer deleteAd = couponService.deleteCoupon(coupon);
        if (deleteAd > 0) {
            BaseRespVo2.setErrno(0);
            BaseRespVo2.setErrmsg("成功");
            return BaseRespVo2;
        }
        return null;
    }

    @RequestMapping("listuser")
    public BaseRespVo listuser() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> hashMap = couponService.listuserCoupon();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
