package com.lxlt.mapper;

import com.lxlt.bean.Category;
import com.lxlt.bean.CategoryExample;
import com.lxlt.bean.CategoryL1Data;
import com.lxlt.bean.CategoryListData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategoryListData> selectByLevel(@Param("level") String level);

    List<CategoryL1Data> selectL1(@Param("level") String level);

    Integer selectPidByid(@Param("catCid") Integer catCid);

    @Select("select id as value, `name` as label from cskaoyanmall_category where pid=#{pid}")
    List<CategoryL1Data> selectCategoryByPid(@Param("pid") String levelOneId);

}
