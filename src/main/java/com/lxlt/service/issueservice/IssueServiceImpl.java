package com.lxlt.service.issueservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.issuebean.Issue;
import com.lxlt.bean.issuebean.IssueExample;
import com.lxlt.bean.issuebean.IssueRep;
import com.lxlt.mapper.IssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    IssueMapper issueMapper;

//分页获取所有通用问题 + 查找
    @Override
    public Map<String,Object> queryIssue(IssueRep issueRep) {
        //开启分页
        PageHelper.startPage(issueRep.getPage(),issueRep.getLimit());
        IssueExample issueExample = new IssueExample();
        IssueExample.Criteria criteria = issueExample.createCriteria();
        //如果问题不空，根据内容查询
        if(StringUtil.isNotEmpty(issueRep.getQuestion())){
            criteria.andQuestionLike("%" + issueRep.getQuestion() + "%");
        }
        criteria.andDeletedEqualTo(false);
        //根据更新时间逆序
        issueExample.setOrderByClause(issueRep.getSort() + " " + issueRep.getOrder());
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issues);
        long total = issuePageInfo.getTotal();
//        System.out.println(total);
//        System.out.println(issuePageInfo);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("items",issues);
        return resultMap;
    }

    //添加问题
    @Override
    public Integer insertIssue(Issue issue) {
        Integer insert = issueMapper.insert(issue);
        return insert;
    }
   //编辑问题
    @Override
    public Integer updateIssue(Issue issue) {
        Integer i = issueMapper.updateByPrimaryKeySelective(issue);
        return i;
    }
   //删除问题
    @Override
    public Integer deleteIssue(Issue issue) {
        issue.setDeleted(true);
        //逻辑删除，将删除的问题deleted置为true
        Integer i = issueMapper.updateByPrimaryKeySelective(issue);
//        int i = issueMapper.deleteByPrimaryKey(issue.getId());
        return i;
    }

}
