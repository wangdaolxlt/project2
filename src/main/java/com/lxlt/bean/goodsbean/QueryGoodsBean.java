package com.lxlt.bean.goodsbean;

import com.lxlt.bean.BaseQueryBean;
import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 15:04
 * <p>
 * 查询goods使用的bean
 * 用于接收goods查询参数
 */
@Data
public class QueryGoodsBean extends BaseQueryBean {

    /**
     * 商品编号
     */
    private String goodsSn;
    /**
     * 商品名称
     */
    private String name;

}
