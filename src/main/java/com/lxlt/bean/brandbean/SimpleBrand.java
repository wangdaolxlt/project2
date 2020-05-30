package com.lxlt.bean.brandbean;

import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 20:37
 *
 * 简单的brand对象, 只包含value即id, label 即 name
 */
@Data
public class SimpleBrand {
    private Integer value;
    private String label;
}
