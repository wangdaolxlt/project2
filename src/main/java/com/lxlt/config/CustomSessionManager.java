package com.lxlt.config;


import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @PackgeName: com.lxlt.config
 * @ClassName: CustomSessionManager
 * @Author: Li Haiquan
 * Date: 2020/6/4 5:18
 * project name: project2
 */

public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 确保session一致
     * @param srequest
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest srequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) srequest;
        String sessionId = request.getHeader("X-cskaoyan-mall-Admin-Token");
        if (sessionId != null && !"".equals(sessionId)){
            return sessionId;
        }

        String sessionId2 = request.getHeader("X-cskaoyanmall-Admin-Token");
        if (sessionId2 != null && !"".equals(sessionId2)){
            return sessionId2;
        }

        return super.getSessionId(request, response);
    }
}
