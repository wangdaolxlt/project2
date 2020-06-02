package com.lxlt.service.wxcartservice;

import com.lxlt.bean.Cart;
import com.lxlt.bean.cartbean.CartCheckedReqVo;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxcartservice
 * @ClassName: WxCartService
 * @Author: Li Haiquan
 * Date: 2020/6/2 9:41
 * project name: project2
 */
public interface WxCartService {
    Map cartIndex(String username);

    int cartGoodsCount(String username);

    Map cartChecked(String username, CartCheckedReqVo cartCheckedReqVo);

    int cartUpdate(Cart cart);

    Map cartDelete(String username, CartCheckedReqVo cartCheckedReqVo);
}
