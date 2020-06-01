package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.logbean.LogReq;
import com.lxlt.service.logservice.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/log")
public class LogController {
    @Autowired
    LogService logService;

    @RequestMapping("list")
    public BaseRespVo listLog(LogReq logReq){
        BaseRespVo allRespVo = new BaseRespVo();
        Map<String, Object> map = logService.listLog(logReq);
        allRespVo.setErrno(0);
        allRespVo.setErrmsg("成功");
        allRespVo.setData(map);
        return allRespVo;
    }
}
