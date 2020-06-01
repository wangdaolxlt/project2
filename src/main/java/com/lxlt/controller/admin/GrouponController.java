package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/30 10:17
 */
@RestController
@RequestMapping("admin/groupon")
public class GrouponController {

    @RequestMapping("list")
    public BaseRespVo list(){
        // TODO: 2020/5/30
        return null;
    }

}
