package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Result;
import com.lxlt.bean.keywordbean.Keyword;
import com.lxlt.bean.keywordbean.KeywordReq;
import com.lxlt.service.keywordservice.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("admin/keyword")
public class KeywordController {

    @Autowired
    KeywordService keywordService;

    //分页查询
    @RequestMapping("list")
    public BaseRespVo keywordList(KeywordReq keywordReq){
        BaseRespVo<Map<String,Object>> keywordRespVo = new BaseRespVo();
        Map<String, Object> map = keywordService.queryKeyword(keywordReq);
        keywordRespVo.setData(map);
        keywordRespVo.setErrno(0);
        keywordRespVo.setErrmsg("成功");
        return keywordRespVo;
    }
    //添加,要在相应的Mapper中添加@Options(useGeneratedKeys = true,keyProperty = "id")可以获取自增id



    @RequestMapping("create")
    public BaseRespVo keywordCreate(@RequestBody Keyword keyword){
        BaseRespVo<Keyword> keywordRespVo = new BaseRespVo();
        keyword.setAddTime(new Date());
        keyword.setUpdateTime(new Date());
        keyword.setDeleted(false);
        keyword.setSortOrder(100);
        Integer integer = keywordService.insertKeyword(keyword);
        if(integer == 1){
            keywordRespVo.setData(keyword);
            keywordRespVo.setErrno(0);
            keywordRespVo.setErrmsg("成功");
        }else{
            keywordRespVo.setErrmsg("失败");
            keywordRespVo.setErrno(10000);
        }
        return keywordRespVo;
    }
    //更新
    @RequestMapping("update")
    public BaseRespVo keywordUpdate(@RequestBody Keyword keyword){
        BaseRespVo<Keyword> keywordRespVo = new BaseRespVo();
        Integer integer = keywordService.updateKeyword(keyword);
        if(integer == 1){
            keywordRespVo.setErrmsg("成功");
            keywordRespVo.setErrno(0);
            keywordRespVo.setData(keyword);
        }else{
            keywordRespVo.setErrmsg("失败");
            keywordRespVo.setErrno(10000);
        }
        return keywordRespVo;
    }

    //删除
    @RequestMapping("delete")
    public Result keywordDelete(@RequestBody Keyword keyword){
        Integer integer = keywordService.deleteKeyword(keyword);
        Result result = new Result();
        if(integer == 1){
            result.setErrmsg("成功");
            result.setErrno(0);
        }else {
            result.setErrno(10000);
            result.setErrmsg("失败");
        }
        return result;
    }
}
