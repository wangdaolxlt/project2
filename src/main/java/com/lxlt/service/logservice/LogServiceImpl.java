package com.lxlt.service.logservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.logbean.Log;
import com.lxlt.bean.logbean.LogExample;
import com.lxlt.bean.logbean.LogReq;
import com.lxlt.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;
    @Override
    public Map<String, Object> listLog(LogReq logReq) {
        PageHelper.startPage(logReq.getPage(),logReq.getLimit());
        LogExample logExample = new LogExample();
        LogExample.Criteria criteria = logExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        logExample.setOrderByClause(logReq.getSort() + " " + logReq.getOrder());
        List<Log> logs = logMapper.selectByExample(logExample);
        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = logPageInfo.getTotal();
        resultMap.put("total",total);
        resultMap.put("items",logs);
        return resultMap;
    }
}
