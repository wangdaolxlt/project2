package com.lxlt.service.keywordservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.keywordbean.Keyword;
import com.lxlt.bean.keywordbean.KeywordExample;
import com.lxlt.bean.keywordbean.KeywordReq;
import com.lxlt.mapper.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordServiceImpl implements KeywordService{

    @Autowired
    KeywordMapper keywordMapper;
    @Override
    public Map<String, Object> queryKeyword(KeywordReq keywordReq) {
        //开启分页
        PageHelper.startPage(keywordReq.getPage(),keywordReq.getLimit());
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        if(StringUtil.isNotEmpty(keywordReq.getKeyword())){
            criteria.andKeywordLike("%" + keywordReq.getKeyword() + "%");
        }
        if(StringUtil.isNotEmpty(keywordReq.getUrl())){
            criteria.andUrlLike("%" + keywordReq.getUrl() + "%");
        }
        criteria.andDeletedEqualTo(false);
        //按照更新时间逆序
        keywordExample.setOrderByClause(keywordReq.getSort() + " " + keywordReq.getOrder());
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>(keywords);
        long total = keywordPageInfo.getTotal();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("items",keywords);
        return resultMap;
    }

    @Override
    public Integer insertKeyword(Keyword keyword) {
        Integer insert = keywordMapper.insert(keyword);
        return insert;
    }

    @Override
    public Integer updateKeyword(Keyword keyword) {
        Integer i = keywordMapper.updateByPrimaryKeySelective(keyword);
        return i;
    }

    @Override
    public Integer deleteKeyword(Keyword keyword) {
        //逻辑删除
        keyword.setDeleted(true);
        int i = keywordMapper.updateByPrimaryKeySelective(keyword);
        return i;
    }
}
