package com.lxlt.service.couponservice;

import com.lxlt.bean.Coupon;
import com.lxlt.bean.CouponExample;
import com.lxlt.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service.couponservice
 * @ClassName: CouponServiceImpl
 * @Author: Pipboy
 * Date: 2020/5/31 15:08
 * project name: project2
 * @Version:
 * @Description:
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public HashMap<String, Object> queryAllCoupons() {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdGreaterThan(0);

        int couponsNum = (int) couponMapper.countByExample(couponExample);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", couponList);
        hashMap.put("total", couponsNum);
        return hashMap;
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andNameIsNotNull();
        coupon.setAddTime(new Date());
        coupon.setUpdateTime(new Date());
        coupon.setId(0);
        couponMapper.insertSelective(coupon);
        return coupon;
    }

    @Override
    public List<Coupon> readCoupon(int id) {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdEqualTo(id);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        return coupons;
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        Integer id = coupon.getId();
        coupon.setUpdateTime(new Date());
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdEqualTo(id);
        couponMapper.updateByExample(coupon, couponExample);
        return coupon;
    }

    @Override
    public Integer deleteCoupon(Coupon coupon) {
        Integer id = coupon.getId();
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdEqualTo(id);
        int key = couponMapper.deleteByPrimaryKey(id);
        return key;
    }

    @Override
    public HashMap<String, Object> listuserCoupon() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", "");
        hashMap.put("total", 0);
        return hashMap;
    }

}
