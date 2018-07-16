package com.crm.interceptor.token;

import java.util.List;

public class JSON_WEB_TOKEN {
    private Integer id;
    private String loginname;
    private List<String> roles;
    private List<String> permissions;

    public JSON_WEB_TOKEN() {
    }

    public JSON_WEB_TOKEN(Integer id, String loginname, List<String> permissions) {
        this.id = id;
        this.loginname = loginname;
        this.permissions = permissions;
    }

    public JSON_WEB_TOKEN(Integer id, List<String> permissions) {
        this.id = id;
        this.permissions = permissions;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "JSON_WEB_TOKEN{" +
                "id=" + id +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
