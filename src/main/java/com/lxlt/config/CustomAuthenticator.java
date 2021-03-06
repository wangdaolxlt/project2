package com.lxlt.config;

import com.lxlt.shiro.token.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @PackgeName: com.lxlt.config
 * @ClassName: CustomAuthenticator
 * @Author: Li Haiquan
 * Date: 2020/6/4 5:18
 * project name: project2
 */
public class CustomAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Collection<Realm> originRealms = this.getRealms();
        //对realms来进行分发
        MallToken token = (MallToken) authenticationToken;
        String type = token.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originRealm : originRealms) {
            //AdminRealm adminrealm admin/wx
            if (originRealm.getName().toLowerCase().contains(type)){
                realms.add(originRealm);
            }
        }

        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) :
                this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
