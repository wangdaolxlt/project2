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
        //filterMap.put("/", "anon");
        filterMap.put("/admin/auth/login", "anon");
        filterMap.put("/wx/auth/login", "anon");
        filterMap.put("/wx/cart/**", "authc");
        filterMap.put("/wx/**", "anon");
        //当你分配了perm1的权限时才能访问need/perm这请求，每个请求都要写很多，不大行
        //filterMap.put("/need/perm","perms[perm1]");
        //优选的方式是声明式
        //filterMap.put("/auth/logout","logout");

        //需要权限认证的接口
        //自己这里写或者声明式
/*        filterMap.put("/admin/category/read","perms[admin:category:read]");
        filterMap.put("/admin/category/update","perms[admin:category:update]");*/
        filterMap.put("admin:admin:update", "perms[/admin/admin/update]");
        filterMap.put("admin:admin:list", "perms[/admin/admin/list]");
        filterMap.put("admin:admin:read", "perms[/admin/admin/read]");
        filterMap.put("admin:admin:delete", "perms[/admin/admin/delete]");
        filterMap.put("admin:admin:create", "perms[/admin/admin/create]");
        filterMap.put("admin:role:permission:update", "perms[/admin/role/permissions]");
        filterMap.put("admin:role:update", "perms[/admin/role/update]");
        filterMap.put("admin:role:list", "perms[/admin/role/list]");
        filterMap.put("admin:role:read", "perms[/admin/role/read]");
        filterMap.put("admin:role:delete", "perms[/admin/role/delete]");
        filterMap.put("admin:role:permission:get", "perms[/admin/role/permissions]");
        filterMap.put("admin:role:create", "perms[/admin/role/create]");
        filterMap.put("admin:storage:update", "perms[/admin/storage/update]");
        filterMap.put("admin:storage:list", "perms[/admin/storage/list]");
        filterMap.put("admin:storage:read", "perms[/admin/storage/read]");
        filterMap.put("admin:storage:delete", "perms[/admin/storage/delete]");
        filterMap.put("admin:storage:create", "perms[/admin/storage/create]");
        filterMap.put("admin:log:list", "perms[/admin/log/list]");
        filterMap.put("admin:brand:update", "perms[/admin/brand/update]");
        filterMap.put("admin:brand:list", "perms[/admin/brand/list]");
        filterMap.put("admin:brand:read", "perms[/admin/brand/read]");
        filterMap.put("admin:brand:delete", "perms[/admin/brand/delete]");
        filterMap.put("admin:brand:create", "perms[/admin/brand/create]");
        filterMap.put("admin:issue:update", "perms[/admin/issue/update]");
        filterMap.put("admin:issue:list", "perms[/admin/issue/list]");
        filterMap.put("admin:issue:delete", "perms[/admin/issue/delete]");
        filterMap.put("admin:issue:create", "perms[/admin/issue/create]");
        filterMap.put("admin:order:read", "perms[/admin/order/detail]");
        filterMap.put("admin:order:reply", "perms[/admin/order/reply]");
        filterMap.put("admin:order:ship", "perms[/admin/order/ship]");
        filterMap.put("admin:order:refund", "perms[/admin/order/refund]");
        filterMap.put("admin:order:list", "perms[/admin/order/list]");
        filterMap.put("admin:category:update", "perms[/admin/category/update]");
        filterMap.put("admin:category:list", "perms[/admin/category/list]");
        filterMap.put("admin:category:read", "perms[/admin/category/read]");
        filterMap.put("admin:category:delete", "perms[/admin/category/delete]");
        filterMap.put("admin:category:create", "perms[/admin/category/create]");
        filterMap.put("admin:keyword:update", "perms[/admin/keyword/update]");
        filterMap.put("admin:keyword:list", "perms[/admin/keyword/list]");
        filterMap.put("admin:keyword:read", "perms[/admin/keyword/read]");
        filterMap.put("admin:keyword:delete", "perms[/admin/keyword/delete]");
        filterMap.put("admin:keyword:create", "perms[/admin/keyword/create]");
        filterMap.put("admin:collect:list", "perms[/admin/collect/list]");
        filterMap.put("admin:footprint:list", "perms[/admin/footprint/list]");
        filterMap.put("admin:feedback:list", "perms[/admin/feedback/list]");
        filterMap.put("admin:history:list", "perms[/admin/history/list]");
        filterMap.put("admin:user:list", "perms[/admin/user/list]");
        filterMap.put("admin:address:list", "perms[/admin/address/list]");
        filterMap.put("admin:config:wx:list", "perms[/admin/config/wx]");
        filterMap.put("admin:config:wx:updateConfigs", "perms[/admin/config/wx]");
        filterMap.put("admin:config:express:list", "perms[/admin/config/express]");
        filterMap.put("admin:config:express:updateConfigs", "perms[/admin/config/express]");
        filterMap.put("admin:config:mall:list", "perms[/admin/config/mall]");
        filterMap.put("admin:config:mall:updateConfigs", "perms[/admin/config/mall]");
        filterMap.put("admin:config:order:list", "perms[/admin/config/order]");
        filterMap.put("admin:config:order:updateConfigs", "perms[/admin/config/order]");
        filterMap.put("admin:ad:update", "perms[/admin/ad/update]");
        filterMap.put("admin:ad:list", "perms[/admin/ad/list]");
        filterMap.put("admin:ad:read", "perms[/admin/ad/read]");
        filterMap.put("admin:ad:delete", "perms[/admin/ad/delete]");
        filterMap.put("admin:ad:create", "perms[/admin/ad/create]");
        filterMap.put("admin:topic:update", "perms[/admin/topic/update]");
        filterMap.put("admin:topic:list", "perms[/admin/topic/list]");
        filterMap.put("admin:topic:read", "perms[/admin/topic/read]");
        filterMap.put("admin:topic:delete", "perms[/admin/topic/delete]");
        filterMap.put("admin:topic:create", "perms[/admin/topic/create]");
        filterMap.put("admin:coupon:listuser", "perms[/admin/coupon/listuser]");
        filterMap.put("admin:coupon:update", "perms[/admin/coupon/update]");
        filterMap.put("admin:coupon:list", "perms[/admin/coupon/list]");
        filterMap.put("admin:coupon:read", "perms[/admin/coupon/read]");
        filterMap.put("admin:coupon:delete", "perms[/admin/coupon/delete]");
        filterMap.put("admin:coupon:create", "perms[/admin/coupon/create]");
        filterMap.put("admin:groupon:read", "perms[/admin/groupon/listRecord]");
        filterMap.put("admin:groupon:update", "perms[/admin/groupon/update]");
        filterMap.put("admin:groupon:list", "perms[/admin/groupon/list]");
        filterMap.put("admin:groupon:delete", "perms[/admin/groupon/delete]");
        filterMap.put("admin:groupon:create", "perms[/admin/groupon/create]");
        filterMap.put("admin:goods:read", "perms[/admin/goods/detail]");
        filterMap.put("admin:goods:update", "perms[/admin/goods/update]");
        filterMap.put("admin:goods:list", "perms[/admin/goods/list]");
        filterMap.put("admin:goods:delete", "perms[/admin/goods/delete]");
        filterMap.put("admin:goods:create", "perms[/admin/goods/create]");
        filterMap.put("admin:comment:list", "perms[/admin/comment/list]");
        filterMap.put("admin:comment:delete", "perms[/admin/comment/delete]");
        filterMap.put("index:permission:write", "perms[/admin/index/write]");
        filterMap.put("index:permission:read", "perms[/admin/index/read]");
        filterMap.put("admin:stat:user", "perms[/admin/stat/user]");
        filterMap.put("admin:stat:order", "perms[/admin/stat/order]");
        filterMap.put("admin:stat:goods", "perms[/admin/stat/goods]");
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
                                                     CustomAuthenticator authenticator) {
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
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /*
     * DefaultWebSessionManager
     * */
    @Bean
    public DefaultWebSessionManager webSessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }


    @Bean
    public CustomAuthenticator authenticator(AdminRealm adminRealm, WxRealm wxRealm) {
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customAuthenticator.setRealms(realms);
        return customAuthenticator;
    }
}
