package com.lxlt.mapper;

import com.lxlt.bean.couponbean.Coupon;
import com.lxlt.bean.CouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    List<Coupon> selectCouponByExampleToIndex(CouponExample example);

    Coupon selectByCode(String code);
}
