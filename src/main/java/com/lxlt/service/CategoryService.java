package com.lxlt.service;

import com.lxlt.bean.BaseRespVo;
import org.apache.ibatis.annotations.Param;



/**
 * @PackgeName: com.lxlt.service
 * @ClassName: CategoryService
 * @Author: Li Haiquan
 * Date: 2020/5/28 21:12
 * project name: project2
 */
public interface CategoryService {
    BaseRespVo selectByLevel(@Param("level") String level);

    BaseRespVo selectL1(@Param("level") String level);
}
