package com.lxlt.mapper;

import com.lxlt.bean.Footprint;
import com.lxlt.bean.FootprintExample;
import com.lxlt.bean.wxfootprintbean.WxFootprint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootprintMapper {
    long countByExample(FootprintExample example);

    int deleteByExample(FootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Footprint record);

    int insertSelective(Footprint record);

    List<Footprint> selectByExample(FootprintExample example);

    Footprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByExample(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByPrimaryKeySelective(Footprint record);

    int updateByPrimaryKey(Footprint record);

    List<WxFootprint> selectByWxExample();
}
