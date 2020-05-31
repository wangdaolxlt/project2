package com.lxlt.service.issueservice;


import com.lxlt.bean.issuebean.Issue;
import com.lxlt.bean.issuebean.IssueRep;
import org.apache.ibatis.annotations.Options;

import java.util.Map;

public interface IssueService {
    //分页查询所有通用问题
    Map<String,Object> queryIssue(IssueRep issueRep);
    //添加问题
    //获取自增主键
    Integer insertIssue(Issue issue);

    //编辑问题
    Integer updateIssue(Issue issue);
    //删除问题
    Integer deleteIssue(Issue issue);
}
