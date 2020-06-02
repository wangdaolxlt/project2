package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.wxindexservice.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/6/2 14:42
 */
@RestController
@RequestMapping("wx/home")
public class WxHomeController {
    @Autowired
    IndexService indexService;

    @RequestMapping("index")
    public BaseRespVo index(){
        BaseRespVo<Map<String, Object>> baseRespVo = new BaseRespVo<>();
        Map<String, Object> resultData = indexService.index();
        if(resultData == null){
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(resultData);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


}
