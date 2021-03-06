package com.lxlt.service.couponservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.bean.CouponExample;
import com.lxlt.bean.couponbean.CouponReq;
import com.lxlt.bean.couponbean.CouponUser;

import com.lxlt.mapper.CartMapper;

import com.lxlt.bean.couponbean.QueryCouponBean;

import com.lxlt.mapper.CouponMapper;
import com.lxlt.mapper.CouponUserMapper;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    CartMapper cartMapper;

    @Override
    public HashMap<String, Object> queryCoupons(QueryCouponBean queryCouponBean) {
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria().andDeletedEqualTo(false);

        PageHelper.startPage(queryCouponBean.getPage(), queryCouponBean.getLimit());
        if(StringUtil.isNotEmpty(queryCouponBean.getName())){
            criteria.andNameLike("%" + queryCouponBean.getName() + "%");
        }
        if(queryCouponBean.getType() != null){
            criteria.andTypeEqualTo(queryCouponBean.getType());
        }
        if(queryCouponBean.getStatus() != null){
            criteria.andStatusEqualTo(queryCouponBean.getStatus());
        }
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(couponList);
        long couponsNum = couponPageInfo.getTotal();

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
        // TODO: 2020/6/4
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
    public HashMap<String, Object> queryWxSelectCoupons(CouponReq couponReq) {
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

    public int insertWxCoupon(CouponUser couponUser) {

        //couponUser.getId();
        //System.out.println(id);
        return couponUserMapper.insertSelective(couponUser);
    }

    @Override
    public void exchangeCouponByCode(String code) {

        Coupon coupon = couponMapper.selectByCode(code);
        //couponUserMapper.insertSelective(coupon);
        return;
    }

    @Override
    public Coupon queryCouponById(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public BigDecimal queryWxGoodsPriceById(Integer cartId) {
        return cartMapper.selectGoodsPriceById(cartId);
    }

    @Override
    public List<Coupon> queryWxEnableCouponList(BigDecimal price) {
        return couponUserMapper.selectEnableCouponList(price);
    }
}
