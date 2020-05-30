package com.lxlt.bean;

import lombok.Data;

import java.util.List;

/**
 * @PackgeName: com.lxlt.bean
 * @ClassName: CategoryL1Data
 * @Author: Li Haiquan
 * Date: 2020/5/28 21:49
 * project name: project2
 */
@Data
public class CategoryL1Data {
    private String value;
    private String label;
    private List<CategoryL1Data> children;
}
