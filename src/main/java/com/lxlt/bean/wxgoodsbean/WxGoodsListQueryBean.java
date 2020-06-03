package com.lxlt.bean.wxgoodsbean;

import com.lxlt.bean.BaseQueryBean;
import lombok.Data;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/3 14:42
 *
 * 前台用于查询goods的bean
 */
@Data
public class WxGoodsListQueryBean extends BaseQueryBean {
    Boolean isHot;
    Boolean isNew;
    Integer categoryId;
    Integer brandId;
    String keyword;
    Integer size;
}
