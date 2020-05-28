package com.lxlt.bean;

import lombok.Data;

import java.util.List;

/**
 * @PackgeName: com.lxlt.bean
 * @ClassName: CategoryListData
 * @Author: Li Haiquan
 * Date: 2020/5/28 20:49
 * project name: project2
 */
@Data
public class CategoryListData {
    private Integer id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private List<CategoryListData> children;
}
