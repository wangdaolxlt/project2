package com.lxlt.service;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Category;
import com.lxlt.bean.CategoryListData;
import org.apache.ibatis.annotations.Param;



/**
 * @PackgeName: com.lxlt.service
 * @ClassName: CategoryService
 * @Author: Li Haiquan
 * Date: 2020/5/28 21:12
 * project name: project2
 */
public interface CategoryService {
    BaseRespVo selectByLevel(String level);

    BaseRespVo selectL1(String level);

    void delete(CategoryListData categoryListData);

    Category create(Category requestCategory);

    void update(Category requestCategory);
}
