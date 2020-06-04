package com.lxlt.shiro.realm;

import com.lxlt.shiro.token.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

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
        //credential = queryPasswordByUsername(username);


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, credential, this.getName());
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
        //List<String> permissionList =
        // 授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }


}
