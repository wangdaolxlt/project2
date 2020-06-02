package com.lxlt.bean.rolebean;

import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.rolebean
 * @ClassName: PostPermissionReqVo
 * @Author: Li Haiquan
 * Date: 2020/6/2 16:58
 * project name: project2
 */
@Data
public class PostPermissionReqVo {
    private String[] permissions;
    private Integer roleId;
}
