package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Result;
import com.lxlt.bean.keywordbean.Keyword;
import com.lxlt.service.wxsearchservice.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/search")
public class WxSearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping("index")
    public BaseRespVo searchIndex(){
        BaseRespVo<Map<String,Object>> baseRespVo = new BaseRespVo();
        Map<String,Object> map = searchService.queryIndex();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(map);
        return baseRespVo;
    }

    //查询之后需要增加一条搜索记录
    @RequestMapping("helper")
    public BaseRespVo searchHelper(String keyword){
        BaseRespVo baseRespVo = new BaseRespVo();
        List<String> list = searchService.searchHelper(keyword);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        //Data里面存放的是匹配到的热门搜索的内容
        baseRespVo.setData(list);
        return baseRespVo;
    }

    //请求负载为空
    @RequestMapping("clearhistory")
    public Result clearHistory(){
        Result result = new Result();
        Integer integer = searchService.clearHistory();
        if(integer >= 1) {
            result.setErrno(0);
            result.setErrmsg("成功");
        }else {
            result.setErrno(10000);
            result.setErrmsg("清空历史搜索失败");
        }
        return result;
    }
}
