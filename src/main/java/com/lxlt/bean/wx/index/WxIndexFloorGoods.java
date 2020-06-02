package com.lxlt.bean.wx.index;

import com.lxlt.bean.Goods;
import lombok.Data;

import java.util.List;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/2 20:52
 */
@Data
public class WxIndexFloorGoods {
    String name;
    List<Goods> goodsList;
    Integer id;
}
