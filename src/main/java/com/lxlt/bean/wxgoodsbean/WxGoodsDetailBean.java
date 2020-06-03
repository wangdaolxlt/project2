package com.lxlt.bean.wxgoodsbean;

import com.lxlt.bean.GoodsSpecification;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 8:42
 */

@Data
public class WxGoodsDetailBean {
    String name;
    List<GoodsSpecification> valueList = new ArrayList<>();
}
