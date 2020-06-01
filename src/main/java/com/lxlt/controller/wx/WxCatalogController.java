package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Category;
import com.lxlt.service.wxcatalogservice.WxCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxCatalogController
 * @Author: Li Haiquan
 * Date: 2020/6/1 19:57
 * project name: project2
 */
@RestController
@RequestMapping("wx/catalog")
public class WxCatalogController {
    @Autowired
    WxCatalogService wxCatalogService;

    @RequestMapping("index")
    public BaseRespVo catalogIndex(){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map =  wxCatalogService.cartIndex();
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

    @RequestMapping("current")
    public BaseRespVo currentCatalog(Category category){
        int id = category.getId();
        BaseRespVo baseRespVo = new BaseRespVo();
        Map map = wxCatalogService.currentCatalog(id);
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
