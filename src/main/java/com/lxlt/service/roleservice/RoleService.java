package com.lxlt.service.roleservice;

import com.lxlt.bean.Role;
import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.bean.rolebean.RoleQueryBean;

import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.roleservice
 * @ClassName: RoleService
 * @Author: Li Haiquan
 * Date: 2020/5/31 11:52
 * project name: project2
 */
public interface RoleService {
    List<RoleOptionsData> queryOptions();

    Map queryRole(RoleQueryBean roleQueryBean);

    Role createRole(Role requestRole);

    int updateRole(Role role);

    int deleteById(Role role);
}
