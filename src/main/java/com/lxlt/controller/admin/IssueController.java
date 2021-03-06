package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Result;
import com.lxlt.bean.issuebean.Issue;
import com.lxlt.bean.issuebean.IssueRep;
import com.lxlt.service.issueservice.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("admin/issue")
public class IssueController {
    @Autowired
    IssueService issueService;
    @RequestMapping("list")
    public BaseRespVo listQuestion(IssueRep issueRep){
        BaseRespVo<Map<String,Object>> issueResp = new BaseRespVo();
        Map<String, Object> map = issueService.queryIssue(issueRep);
        issueResp.setErrno(0);
        issueResp.setData(map);
        issueResp.setErrmsg("成功");
        return issueResp;
    }
   //添加
    @RequestMapping("create")
    public BaseRespVo creatQuestion(@RequestBody Issue issue){
        BaseRespVo<Issue> addResult = new BaseRespVo<>();
        issue.setAddTime(new Date());
        issue.setUpdateTime(new Date());
        issue.setDeleted(false);
        Integer integer = issueService.insertIssue(issue);
        if(integer == 1) {
            addResult.setErrno(0);
            addResult.setErrmsg("成功");
            addResult.setData(issue);
        }else {
            addResult.setErrmsg("失败");
            addResult.setErrno(10000);
        }
        return addResult;
    }

    //更新
    @RequestMapping("update")
    public BaseRespVo updateQuestion(@RequestBody Issue issue){
        issue.setUpdateTime(new Date());
        BaseRespVo<Issue> updateResult = new BaseRespVo<>();
        Integer integer = issueService.updateIssue(issue);
        if(integer == 1) {
            updateResult.setData(issue);
            updateResult.setErrmsg("成功");
            updateResult.setErrno(0);
        }else {
            updateResult.setErrmsg("失败");
            updateResult.setErrno(10000);
        }
        return updateResult;
    }

    //删除
    @RequestMapping("delete")
    public Result deleteQuestion(@RequestBody Issue issue){
        Integer integer = issueService.deleteIssue(issue);
        Result result = new Result();
        if(integer == 1){
            result.setErrmsg("成功");
            result.setErrno(0);
        }else{
            result.setErrno(10000);
            result.setErrmsg("失败");
        }
        return result;
    }
}
