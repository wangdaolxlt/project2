package com.lxlt.shiro.realm;

import com.lxlt.bean.Admin;
import com.lxlt.bean.AdminExample;
import com.lxlt.bean.Permission;
import com.lxlt.bean.PermissionExample;
import com.lxlt.mapper.AdminMapper;
import com.lxlt.mapper.PermissionMapper;
import com.lxlt.service.authservice.AuthService;
import com.lxlt.shiro.token.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackgeName: com.lxlt.shiro.realm
 * @ClassName: AdminRealm
 * @Author: Li Haiquan
 * Date: 2020/6/4 5:19
 * project name: project2
 */
@Component
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * Admin认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        MallToken token = (MallToken) authenticationToken;

        String username = token.getUsername();
        //自己写查询语句
        String password = adminMapper.getPasswordByUsername(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());
        return authenticationInfo;
    }

    /**
     * Admin授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 用admin的username去查需要哪些权限，admin-role-permissions

        List<String> permissionList = getPermByUsername(username);
        // 授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }

    private List<String> getPermByUsername(String username) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andDeletedEqualTo(false).andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        Admin admin = admins.get(0);
        List<Integer> roleIds = admin.getRoleIds();
        List<Permission> permissions = null;
        if(roleIds != null && roleIds.size() != 0){
            PermissionExample permissionExample = new PermissionExample();
            permissionExample.createCriteria().andDeletedEqualTo(false).andRoleIdIn(roleIds);
            permissions = permissionMapper.selectByExample(permissionExample);
        }

        List<String> permissionList = new ArrayList<>();
        if (permissions == null || permissions.size() == 0){
            return permissionList;
        }
        for (Permission permission : permissions) {
            permissionList.add(permission.getPermission());
        }
        return permissionList;

    }

}
