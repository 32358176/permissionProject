package com.crm.utils;

import java.util.List;

public class SystemUserInfo {
    private Integer userId;
    private String userName;
    private List<Integer> rolesID;
    private List<Integer> modulesID;
    private String token;

    public SystemUserInfo() {
    }

    public SystemUserInfo(Integer userId, String userName, List<Integer> rolesID, List<Integer> modulesID, String token) {
        this.userId = userId;
        this.userName = userName;
        this.rolesID = rolesID;
        this.modulesID = modulesID;

        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Integer> getRolesID() {
        return rolesID;
    }

    public void setRolesID(List<Integer> rolesID) {
        this.rolesID = rolesID;
    }

    public List<Integer> getModulesID() {
        return modulesID;
    }

    public void setModulesID(List<Integer> modulesID) {
        this.modulesID = modulesID;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SystemUserInfoSystemUserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", rolesID=" + rolesID +
                ", modulesID=" + modulesID +
                ", token='" + token + '\'' +
                '}';
    }
}

