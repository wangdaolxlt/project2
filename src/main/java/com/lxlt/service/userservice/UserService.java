package com.lxlt.service.userservice;

import java.util.HashMap;
import java.util.List;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: UserService
 * @Author: admin
 * Date: 2020/5/29 11:31
 * project name: project2
 * @Version:
 * @Description:
 */
public interface UserService {

    HashMap<String, Object> queryAllUsers();

    List<String> queryAllOrders();
}
