package com.lxlt.bean.userbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackgeName: com.lxlt.bean.userbean
 * @ClassName: WxUserInfo
 * @Author: Li Haiquan
 * Date: 2020/6/1 18:32
 * project name: project2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserInfo {
    private String nickname;
    private String avatarUrl;
}
