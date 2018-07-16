package com.crm.pojo.mysql;

import java.io.Serializable;
import java.util.Date;

public class Permissions implements Serializable {
    private Integer id;

    private String permissionvalue;

    private String permissionname;

    private String permissionmodule;

    private Date permissionlastupdatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionvalue() {
        return permissionvalue;
    }

    public void setPermissionvalue(String permissionvalue) {
        this.permissionvalue = permissionvalue == null ? null : permissionvalue.trim();
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    public String getPermissionmodule() {
        return permissionmodule;
    }

    public void setPermissionmodule(String permissionmodule) {
        this.permissionmodule = permissionmodule == null ? null : permissionmodule.trim();
    }

    public Date getPermissionlastupdatetime() {
        return permissionlastupdatetime;
    }

    public void setPermissionlastupdatetime(Date permissionlastupdatetime) {
        this.permissionlastupdatetime = permissionlastupdatetime;
    }
}