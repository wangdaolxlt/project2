package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.GrouponRules;
import com.lxlt.bean.grouponbean.QueryGrouponRulesBean;
import com.lxlt.service.grouponrulesservice.GrouponRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/30 10:17
 */
@RestController
@RequestMapping("admin/groupon")
public class GrouponRulesController {

    @Autowired
    GrouponRulesService grouponRulesService;

    @RequestMapping("list")
    public BaseRespVo list(QueryGrouponRulesBean queryGrouponRulesBean) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Map<String, Object> dataMap = grouponRulesService.queryGrouponRules(queryGrouponRulesBean);
        if (dataMap == null || dataMap.size() != 2) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(dataMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 添加到
     * @param grouponRules
     * @return
     */
    @RequestMapping("create")
    public BaseRespVo createGrouponRules(@RequestBody GrouponRules grouponRules) {
        BaseRespVo baseRespVo = new BaseRespVo();

        int code = grouponRulesService.createGrouponRules(grouponRules);
        if (code == 400) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("不存在该商品id");
            return baseRespVo;
        }
        if (code != 200) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setData(grouponRules);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody GrouponRules grouponRules) {
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = grouponRulesService.update(grouponRules);
        if (code == 400) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("不存在该商品id");
            return baseRespVo;
        }
        if (code != 200) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody GrouponRules grouponRules) {
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = grouponRulesService.delete(grouponRules);
        if (code != 200) {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
