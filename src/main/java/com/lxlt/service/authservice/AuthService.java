package com.lxlt.service.authservice;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.authservice
 * @ClassName: AuthService
 * @Author: Li Haiquan
 * Date: 2020/6/4 9:49
 * project name: project2
 */
public interface AuthService {
    void createFailAdminLog(String username);

    void createSuccessfulAdminLog(String username);

    Map getInfo(String username);

    void createAdminLogoutLog(String username);
}
