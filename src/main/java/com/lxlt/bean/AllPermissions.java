package com.lxlt.bean;

import java.util.List;

public class AllPermissions {
    private Integer primaryId;

    private String id;

    private String label;

    private Integer pid;

    private String api;

    private List<AllPermissions> children;

    public List<AllPermissions> getChildren() {
        return children;
    }

    public void setChildren(List<AllPermissions> children) {
        this.children = children;
    }

    public Integer getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Integer primaryId) {
        this.primaryId = primaryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api == null ? null : api.trim();
    }
}
