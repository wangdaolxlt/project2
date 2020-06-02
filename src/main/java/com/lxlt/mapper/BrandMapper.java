package com.lxlt.mapper;

import com.lxlt.bean.brandbean.Brand;
import com.lxlt.bean.BrandExample;
import com.lxlt.bean.brandbean.SimpleBrand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Integer insertSelectiveAndReturnId(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    @Select("select id as value, `name` as label from cskaoyanmall_brand")
    List<SimpleBrand> selectAllSimpleBrand();

    List<Brand> selectBrandByExampleToIndex(BrandExample brandExample);
}
