package com.lxlt.bean;

import java.util.List;

/**
 * @PackgeName: com.lxlt.bean
 * @ClassName: InfoRespVo
 * @Author: Li Haiquan
 * Date: 2020/5/28 16:58
 * project name: project2
 */
public class InfoRespVo {

    /**
     * errno : 0
     * data : {"roles":["超级管理员"],"name":"admin123","perms":["*"],"avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}
     * errmsg : 成功
     */

    private int errno;
    private DataBean data;
    private String errmsg;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static class DataBean {
        /**
         * roles : ["超级管理员"]
         * name : admin123
         * perms : ["*"]
         * avatar : https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif
         */

        private String name;
        private String avatar;
        private List<String> roles;
        private List<String> perms;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public List<String> getPerms() {
            return perms;
        }

        public void setPerms(List<String> perms) {
            this.perms = perms;
        }
    }
}
