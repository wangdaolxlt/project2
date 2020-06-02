package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.configbean.ConfigBean;
import com.lxlt.service.configservice.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @PackgeName: ConfigController
 * @date: 2020/6/1
 * @Description:
 */
@RestController
@RequestMapping("admin/config")
public class ConfigController {
    @Autowired
    ConfigService configService;

    /**
     * 商场配置
     */
    @RequestMapping("mall")
    public BaseRespVo getMallConfigList(@RequestBody(required = false) Map<String, String> map){
        if (map != null){
            if (configService.updateConfig(map)){
                outputFailedResult();

            }
        }
        return getConfig("'cskaoyanmall_mall%'");
    }
    private BaseRespVo outputFailedResult(){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(null);
        baseRespVo.setErrmsg("失败");
        baseRespVo.setErrno(500);
        return baseRespVo;
    }
    private BaseRespVo getConfig(String type){
        List<ConfigBean> configBeanList = configService.getConfig(type);
        HashMap<String, String> data = new HashMap<>();

        for (ConfigBean configBean : configBeanList) {
            data.put(configBean.getBeanKey().getBeanKey(),configBean.getBeanValue().getBeanValue());
        }

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(data);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    /**
     * 运费配置
     */
    @RequestMapping("express")
    public BaseRespVo getExpressConfigList(@RequestBody(required = false) Map<String,String> map){
        if (map != null){
            if (configService.updateConfig(map)){
                outputFailedResult();
            }
        }
        return getConfig("'cskaoyanmall_express%'");
    }

    /**
     * 订单配置
     */
    @RequestMapping("order")
    public BaseRespVo getOrderConfigList(@RequestBody(required = false) Map<String,String> map){
        if (map != null){
            if (configService.updateConfig(map)){
                outputFailedResult();
            }
        }
        return getConfig("'cskaoyanmall_order%'");
    }

    /**
     * 小程序配置
     */
    @RequestMapping("wx")
    public BaseRespVo getWxConfigList(@RequestBody(required = false) Map<String,String> map){
        if (map != null){
            if (configService.updateConfig(map)){
                outputFailedResult();
            }
        }
        return getConfig("'cskaoyanmall_wx%'");
    }
}
