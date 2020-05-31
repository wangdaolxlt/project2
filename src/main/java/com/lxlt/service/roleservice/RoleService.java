package com.lxlt.service.roleservice;

import com.lxlt.bean.rolebean.RoleOptionsData;

import java.util.List;

/**
 * @PackgeName: com.lxlt.service.roleservice
 * @ClassName: RoleService
 * @Author: Li Haiquan
 * Date: 2020/5/31 11:52
 * project name: project2
 */
public interface RoleService {
    List<RoleOptionsData> queryOptions();
}
