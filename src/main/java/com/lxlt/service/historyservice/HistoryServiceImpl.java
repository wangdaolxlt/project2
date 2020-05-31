package com.lxlt.service.historyservice;

import com.lxlt.bean.SearchHistory;
import com.lxlt.bean.SearchHistoryExample;
import com.lxlt.mapper.SearchHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: HistoryServiceImpl
 * @Author: admin
 * Date: 2020/5/29 15:49
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public HashMap<String, Object> queryAllHistories() {
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        searchHistoryExample.createCriteria().andIdGreaterThan(0);

        int historiesNum = (int) searchHistoryMapper.countByExample(searchHistoryExample);
        List<SearchHistory> searchHistoryList = searchHistoryMapper.selectByExample(searchHistoryExample);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("items", searchHistoryList);
        hashMap.put("total", historiesNum);
        return hashMap;
    }
}
