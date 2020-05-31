package com.lxlt.service.adminservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.adminbean.Admin;
import com.lxlt.bean.adminbean.AdminExample;
import com.lxlt.bean.adminbean.AdminReq;
import com.lxlt.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Map<String, Object> queryAdmin(AdminReq adminReq) {
        //分页
        PageHelper.startPage(adminReq.getPage(),adminReq.getLimit());
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        if(StringUtil.isNotEmpty(adminReq.getUsername())){
            criteria.andUsernameLike("%" + adminReq.getUsername() + "%");
        }
        criteria.andDeletedEqualTo(false);
        //根据时间更新
        adminExample.setOrderByClause(adminReq.getSort() + " " + adminReq.getOrder());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("items",admins);
        return resultMap;
    }

    @Override
    public Integer insertAdmin(Admin admin) {
        return null;
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        return null;
    }

    @Override
    public Integer deleteAdmin(Admin admin) {
        return null;
    }
}
