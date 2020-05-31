package com.lxlt.service.roleservice;

import com.lxlt.bean.rolebean.RoleOptionsData;
import com.lxlt.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackgeName: com.lxlt.service.roleservice
 * @ClassName: RoleServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/5/31 11:52
 * project name: project2
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<RoleOptionsData> queryOptions() {
        List<RoleOptionsData> roleOptionsDataList = roleMapper.queryOptions();
        return roleOptionsDataList;
    }
}
