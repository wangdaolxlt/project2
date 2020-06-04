package com.lxlt.shiro.realm;

import com.lxlt.shiro.token.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @PackgeName: com.lxlt.shiro.realm
 * @ClassName: WxRealm
 * @Author: Li Haiquan
 * Date: 2020/6/4 5:19
 * project name: project2
 */
@Component
public class WxRealm extends AuthorizingRealm {

    /**
     * wx认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        MallToken token = (MallToken) authenticationToken;

        String username = token.getUsername();
        // 获取密码
        // credential = wxUserService.getPasswordByUsername(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, credential, this.getName());
        return authenticationInfo;
    }

    /**
     * wx授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
