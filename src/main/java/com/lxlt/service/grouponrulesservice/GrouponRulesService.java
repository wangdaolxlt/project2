package com.lxlt.service.grouponrulesservice;

import com.lxlt.bean.GrouponRules;
import com.lxlt.bean.grouponbean.QueryGrouponRulesBean;

import java.util.Map;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/30 11:00
 */
public interface GrouponRulesService {
    /**
     * 查询groupRules
     * @param queryGrouponRulesBean
     * @return
     */
    Map<String, Object> queryGrouponRules(QueryGrouponRulesBean queryGrouponRulesBean);

    /**
     * 创建grouponRules,持久到数据库
     * @param grouponRules
     * @return
     */
    int createGrouponRules(GrouponRules grouponRules);

    /**
     * 更新groupRules, 需要判断goodsId是否存在
     * @param grouponRules
     * @return 400表示商品不存在,500表示服务器内部错误,200表示成功
     */
    int update(GrouponRules grouponRules);

    /**
     * 删除groupRules
     * @param grouponRules
     * @return
     */
    int delete(GrouponRules grouponRules);
}
