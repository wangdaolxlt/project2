package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.wxcatalogservice.WxCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @RequestMapping("index")
    public BaseRespVo cartIndex(){
        BaseRespVo baseRespVo = new BaseRespVo();

        return baseRespVo;
    }
}
