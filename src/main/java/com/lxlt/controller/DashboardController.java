package com.lxlt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 17:31
 */
@RestController
@RequestMapping("admin")
public class DashboardController {

    @RequestMapping("dashboard")
    public String dashboard(){
        return "{\n" +
                "\t\"errno\": 0,\n" +
                "\t\"data\": {\n" +
                "\t\t\"goodsTotal\": 248,\n" +
                "\t\t\"userTotal\": 3,\n" +
                "\t\t\"productTotal\": 255,\n" +
                "\t\t\"orderTotal\": 73\n" +
                "\t},\n" +
                "\t\"errmsg\": \"成功\"\n" +
                "}";
    }
}
