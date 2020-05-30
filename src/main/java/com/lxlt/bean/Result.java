package com.lxlt.bean;

public class Result {

    /**
     * errno : 0
     * errmsg : 成功
     */

    private Integer errno;
    private String errmsg;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
