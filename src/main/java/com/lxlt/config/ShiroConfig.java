package com.lxlt.config;

import com.lxlt.shiro.realm.AdminRealm;
import com.lxlt.shiro.realm.WxRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @PackgeName: com.lxlt.config
 * @ClassName: ShiroConfig
 * @Author: Li Haiquan
 * Date: 2020/6/3 9:41
 * project name: project2
 */
@Configuration
public class ShiroConfig {

    /*
     * ShiroFilterFactoryBean
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //认证失败后重定向的url
        shiroFilterFactoryBean.setLoginUrl("/#/login");
        // 声明工厂使用哪一个securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //最重要的事情
        //对请求过滤 filter
        //key--请求url    value---过滤器（不同的简写对应不同的过滤器）
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        //login(username,password) success,失败则重新登录 有顺序的！！要用linked
        // anno即允许匿名访问不设拦截， authc进行认证拦截（过了认证的才能通过） perms进行授权拦截，有权限的才能过
        filterMap.put("/", "anon");
        filterMap.put("/admin/auth/login", "anon");
        filterMap.put("/wx/auth/login", "anon");
        filterMap.put("/wx/**", "anon");
        //当你分配了perm1的权限时才能访问need/perm这请求，每个请求都要写很多，不大行
        //filterMap.put("/need/perm","perms[perm1]");
        //优选的方式是声明式
        //filterMap.put("/auth/logout","logout");

        //需要权限认证的接口
        //自己这里写或者声明式
/*        filterMap.put("/admin/category/read","perms[admin:category:read]");
        filterMap.put("/admin/category/update","perms[admin:category:update]");*/

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Autowired
    AdminRealm adminRealm;
    @Autowired
    WxRealm wxRealm;


    /*
     * SecurityManager
     *
     * */
    @Bean
    public DefaultWebSecurityManager securityManager(DefaultWebSessionManager webSessionManager,
                                                     CustomAuthenticator authenticator){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        ArrayList<Realm> arrayList = new ArrayList<>();
        arrayList.add(adminRealm);
        arrayList.add(wxRealm);
        defaultWebSecurityManager.setRealms(arrayList);
        defaultWebSecurityManager.setAuthenticator(authenticator);
        defaultWebSecurityManager.setSessionManager(webSessionManager);
        return defaultWebSecurityManager;
    }


    /*
     * 声明式鉴权 注解需要的组件
     *AuthorizationAttributeSourceAdvisor
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /*
     * DefaultWebSessionManager
     * */
    @Bean
    public DefaultWebSessionManager webSessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }


    @Bean
    public CustomAuthenticator authenticator(AdminRealm adminRealm, WxRealm wxRealm){
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customAuthenticator.setRealms(realms);
        return customAuthenticator;
    }
}
