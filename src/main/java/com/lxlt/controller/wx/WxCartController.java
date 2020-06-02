package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Cart;
import com.lxlt.bean.cartbean.CartCheckedReqVo;
import com.lxlt.service.wxcartservice.WxCartService;
import com.lxlt.service.wxcatalogservice.WxCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxCartcontroller
 * @Author: Li Haiquan
 * Date: 2020/6/1 19:47
 * project name: project2
 */
@RestController
@RequestMapping("wx/cart")
public class WxCartController {

    @Autowired
    WxCartService wxCartService;

    /**
     * 购物车首页
     * @return
     */
    @RequestMapping("index")
    public BaseRespVo cartIndex(){
        BaseRespVo baseRespVo = new BaseRespVo();
        //预设一个username
        String username = "test1";
        Map map = wxCartService.cartIndex(username);
        if(map == null){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(map);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 购物车总数
     * @return
     */
    @RequestMapping("goodscount")
    public BaseRespVo cartGoodsCount(){
        BaseRespVo baseRespVo = new BaseRespVo();
        String username = "test1";
        int count = wxCartService.cartGoodsCount(username);
        baseRespVo.setData(count);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 是否选择
     * @param cartCheckedReqVo
     * @return
     */
    @RequestMapping("checked")
    public BaseRespVo cartChecked(@RequestBody CartCheckedReqVo cartCheckedReqVo){
        BaseRespVo baseRespVo = new BaseRespVo();
        //预设一个username
        String username = "test1";
        Map map = wxCartService.cartChecked(username, cartCheckedReqVo);
        if(map == null){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(map);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


    /**
     *更新购物车
     * @param cart
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo cartUpdate(@RequestBody Cart cart){
        BaseRespVo baseRespVo = new BaseRespVo();

        int code = wxCartService.cartUpdate(cart);
        if(code == 502){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 删除
     * @param cartCheckedReqVo
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo cartDelete(@RequestBody CartCheckedReqVo cartCheckedReqVo){
        BaseRespVo baseRespVo = new BaseRespVo();
        //预设一个username
        String username = "test1";
        Map map = wxCartService.cartDelete(username, cartCheckedReqVo);
        if(map == null){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(map);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
