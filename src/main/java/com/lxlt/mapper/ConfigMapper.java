package com.lxlt.mapper;

import com.lxlt.bean.configbean.ConfigBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lenovo
 * @PackgeName: ConfigMapper
 * @date: 2020/6/1
 * @Description:
 */
public interface ConfigMapper {
    List<ConfigBean> getConfig(@Param("type") String type);

}
