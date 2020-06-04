package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Collect;
import com.lxlt.bean.wxcollectbean.WxCollectReqVo;
import com.lxlt.service.wxcollectservice.WxCollectService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxCollectController
 * @Author: Li Haiquan
 * Date: 2020/6/4 2:45
 * project name: project2
 */
@RestController
@RequestMapping("wx/collect")
public class WxCollectController {
    @Autowired
    WxCollectService wxCollectService;

    @RequestMapping("list")
    public BaseRespVo collectList(WxCollectReqVo wxCollectReqVo){
        BaseRespVo baseRespVo = new BaseRespVo();
/*        //预设一个username
        String username = "test1";*/
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Map map = wxCollectService.collectList(username, wxCollectReqVo);
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

    @RequestMapping("addordelete")
    public BaseRespVo collectAddOrDelete(@RequestBody Collect collect){
        BaseRespVo baseRespVo = new BaseRespVo();
/*        //预设一个username
        String username = "test1";*/
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Map map = wxCollectService.collectAddOrDelete(username, collect);
        baseRespVo.setData(map);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
