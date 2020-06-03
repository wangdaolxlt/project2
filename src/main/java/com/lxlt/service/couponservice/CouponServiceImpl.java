package com.lxlt.service.couponservice;

import com.github.pagehelper.PageHelper;
import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.bean.CouponExample;
import com.lxlt.bean.couponbean.CouponReq;
import com.lxlt.bean.couponbean.CouponUser;
import com.lxlt.mapper.CouponMapper;
import com.lxlt.mapper.CouponUserMapper;
import org.apache.shiro.crypto.hash.Hash;
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

    @Autowired
    CouponUserMapper couponUserMapper;

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

    @Override
    public HashMap<String, Object> queryWxAllCoupons(CouponReq couponReq) {
        PageHelper.startPage(couponReq.getPage(), couponReq.getSize());
        HashMap<String, Object> hashMap = new HashMap<>();
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdGreaterThan(0);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        int couponCount = (int) couponMapper.countByExample(couponExample);
        hashMap.put("data", couponList);
        hashMap.put("count", couponCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryWxAllMyCoupons(CouponReq couponReq) {
        PageHelper.startPage(couponReq.getPage(), couponReq.getSize());
        HashMap<String, Object> hashMap = new HashMap<>();
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdGreaterThan(0).andStatusEqualTo(couponReq.getStatus());
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        int couponCount = (int) couponMapper.countByExample(couponExample);
        hashMap.put("data", couponList);
        hashMap.put("count", couponCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryWxselectCoupons(CouponReq couponReq) {
        PageHelper.startPage(couponReq.getPage(), couponReq.getSize());
        HashMap<String, Object> hashMap = new HashMap<>();
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdGreaterThan(0);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        int couponCount = (int) couponMapper.countByExample(couponExample);
        hashMap.put("data", couponList);
        hashMap.put("count", couponCount);
        return hashMap;
    }

    @Override
    public Boolean insertWxCoupon(CouponUser couponUser) {
        //int id = couponUserMapper.insertSelective(couponUser);
        //couponUser.getId();
        //System.out.println(id);
        return null;
    }

    @Override
    public void exchangeCouponByCode(String code) {
        Coupon coupon = couponMapper.selectByCode(code);
        //couponUserMapper.insertSelective(coupon);
        return;
    }
}
