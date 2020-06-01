package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.dashboardservice.DashboradService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 17:31
 */
@RestController
@RequestMapping("admin")
public class DashboardController {

    @Autowired
    DashboradService dashboradService;

    @RequestMapping("dashboard")
    public BaseRespVo dashboard() {
        BaseRespVo<HashMap> baseRespVo = new BaseRespVo<>();
        HashMap<String, Integer> hashMap = dashboradService.queryAllCounts();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


}
