package com.lxlt.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @PackgeName: com.lxlt.shiro.token
 * @ClassName: MallToken
 * @Author: Li Haiquan
 * Date: 2020/6/4 5:19
 * project name: project2
 */
@Data
public class MallToken extends UsernamePasswordToken {
    String type;

    public MallToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
