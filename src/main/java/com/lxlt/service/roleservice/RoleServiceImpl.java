package com.lxlt.service.roleservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.*;
import com.lxlt.bean.rolebean.PostPermissionReqVo;
import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.bean.rolebean.RoleQueryBean;
import com.lxlt.mapper.AdminMapper;
import com.lxlt.mapper.AllPermissionsMapper;
import com.lxlt.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    AllPermissionsMapper allPermissionsMapper;

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
    public Map createRole(Role requestRole) {
        Map<String, Object> map = new HashMap();
        //对角色名进行查重
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdGreaterThanOrEqualTo(0);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        for (int i = 0; i < roles.size(); i++) {
            if(requestRole.getName().equals(roles.get(i).getName())){
                //角色已存在
                map.put("code", 640);
                return map;
            }
        }
        Date date = new Date();
        requestRole.setEnabled(true);
        requestRole.setDeleted(false);
        requestRole.setAddTime(date);
        requestRole.setUpdateTime(date);
        List list = new ArrayList();
        requestRole.setPermissions(list);
        int code = roleMapper.insertSelective(requestRole);
        if(code != 1){
            map.put("code", 502);
            return map;
        }
        map.put("code", 200);
        map.put("role", requestRole);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(Role role) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdNotEqualTo(role.getId());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        for (int i = 0; i < roles.size(); i++) {
            if(role.getName().equals(roles.get(i).getName())){
                //角色已存在
                return 640;
            }
        }
        role.setUpdateTime(new Date());
        int update = roleMapper.updateByPrimaryKey(role);
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
        role.setUpdateTime(new Date());
        role.setDeleted(true);
        int code = roleMapper.updateByPrimaryKey(role);
        if(code != 1){
            return 501;
        }
        return 200;
    }

    @Override
    public Map roleGetPermissions(int roleId) {
        //显示所有的systemPermissions
        AllPermissionsExample allPermissionsExampleL1 = new AllPermissionsExample();
        allPermissionsExampleL1.createCriteria().andPidEqualTo(0);
        List<AllPermissions> level1Permissions = allPermissionsMapper.selectByExample(allPermissionsExampleL1);
        for (int i = 0; i < level1Permissions.size(); i++) {
            int level1PrimaryId = level1Permissions.get(i).getPrimaryId();
            AllPermissionsExample allPermissionsExampleL2 = new AllPermissionsExample();
            allPermissionsExampleL2.createCriteria().andPidEqualTo(level1PrimaryId);
            List<AllPermissions> level2Permissions = allPermissionsMapper.selectByExample(allPermissionsExampleL2);
            for (int j = 0; j < level2Permissions.size(); j++) {
                int level2PrimaryId = level2Permissions.get(j).getPrimaryId();
                AllPermissionsExample allPermissionsExampleL3 = new AllPermissionsExample();
                allPermissionsExampleL3.createCriteria().andPidEqualTo(level2PrimaryId);
                List<AllPermissions> level3Permissions = allPermissionsMapper.selectByExample(allPermissionsExampleL3);
                level2Permissions.get(j).setChildren(level3Permissions);
            }
            level1Permissions.get(i).setChildren(level2Permissions);
        }
        Map map = new HashMap();

        map.put("systemPermissions", level1Permissions);
        //通过roleId取出所有的permissions的primaryId
        Role role = roleMapper.selectByPrimaryKey(roleId);
        List assignedPermissionsIds = role.getPermissions();
        //通过primaryId查出所有的permission
        //没有权限返回空
        if(assignedPermissionsIds.size() == 0){
            map.put("assignedPermissions", null);
            return map;
        }
        //有权限进行查询,有哪些权限
        AllPermissionsExample allPermissionsExample = new AllPermissionsExample();
        allPermissionsExample.createCriteria().andPrimaryIdIn(assignedPermissionsIds);
        List<AllPermissions> allPermissions = allPermissionsMapper.selectByExample(allPermissionsExample);
        List assignedPermissions = new ArrayList();
        for (int i = 0; i < allPermissions.size(); i++) {
            assignedPermissions.add(allPermissions.get(i).getId());
        }
        map.put("assignedPermissions", assignedPermissions);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int rolePostPermissions(PostPermissionReqVo postPermissionReqVo) {
        List permissionsList = new ArrayList();
        if(postPermissionReqVo.getPermissions().length != 0){
            //查出所有的permissions的primaryId
            AllPermissionsExample allPermissionsExample = new AllPermissionsExample();
            allPermissionsExample.createCriteria().andIdIn(Arrays.asList(postPermissionReqVo.getPermissions()));
            List<AllPermissions> allPermissions = allPermissionsMapper.selectByExample(allPermissionsExample);
            for (int i = 0; i < allPermissions.size(); i++) {
                permissionsList.add(allPermissions.get(i).getPrimaryId());
            }
        }
        //更新数据
        Role role = roleMapper.selectByPrimaryKey(postPermissionReqVo.getRoleId());
        role.setUpdateTime(new Date());
        role.setPermissions(permissionsList);
        int code = roleMapper.updateByPrimaryKey(role);
        if(code != 1){
            return 502;
        }
        return 200;
    }
}
