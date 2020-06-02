package com.lxlt.bean.wx.index;

import com.lxlt.bean.Goods;
import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/2 16:56
 */
@Data
public class WxIndexGroupon {
    /**
     * 前端以此种格式接收, 所以不以驼峰命名
     */
    Integer groupon_price;
    Goods goods;
    Integer groupon_member;
}
