package com.lxlt.service.roleservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.*;
import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.bean.rolebean.RoleQueryBean;
import com.lxlt.mapper.AdminMapper;
import com.lxlt.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.roleservice
 * @ClassName: RoleServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/5/31 11:52
 * project name: project2
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<RoleOptionsData> queryOptions() {
        List<RoleOptionsData> roleOptionsDataList = roleMapper.queryOptions();
        return roleOptionsDataList;
    }

    @Override
    public Map queryRole(RoleQueryBean roleQueryBean) {
        PageHelper.startPage(roleQueryBean.getPage(), roleQueryBean.getLimit());
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        //角色名称模糊查询
        if(StringUtil.isNotEmpty(roleQueryBean.getName())){
            criteria.andNameLike("%" + roleQueryBean.getName() + "%");
        }
        roleExample.setOrderByClause(roleQueryBean.getSort() + " " + roleQueryBean.getOrder());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo(roles);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map .put("items", roles);
        map .put("total", total);
        return map ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role createRole(Role requestRole) {
        Date date = new Date();
        requestRole.setEnabled(true);
        requestRole.setDeleted(false);
        requestRole.setAddTime(date);
        requestRole.setUpdateTime(date);
        roleMapper.insertSelective(requestRole);
        return requestRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(Role role) {
        role.setUpdateTime(new Date());
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdEqualTo(role.getId());
        int update = roleMapper.updateByExampleSelective(role, roleExample);
        if(update != 1){
            return 500;
        }
        return 200;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Role role) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdGreaterThanOrEqualTo(0);
        // 查询admin中是否有管理员存在当前角色
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        for (int i = 0; i < admins.size(); i++) {
            if(admins.get(i).getRoleIds().contains(role.getId())){
                return 500;
            }
        }
        //确保管理员没有使用到该角色，就可以逻辑删除
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdEqualTo(role.getId());
        role.setUpdateTime(new Date());
        role.setDeleted(true);
        int code = roleMapper.updateByExampleSelective(role, roleExample);
        if(code != 1){
            return 501;
        }
        return 200;
    }
}
