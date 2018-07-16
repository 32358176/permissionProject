package com.crm.pojo;

import java.io.Serializable;

public class Logmessage implements Serializable {
    private Integer id;

    private String username;

    private String roles;

    private String status;

    private String methodname;

    private String argus;

    private String desciption;

    private String startdatetime;

    private String enddatetime;

    private String time;

    private String exception;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname == null ? null : methodname.trim();
    }

    public String getArgus() {
        return argus;
    }

    public void setArgus(String argus) {
        this.argus = argus == null ? null : argus.trim();
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption == null ? null : desciption.trim();
    }

    public String getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime == null ? null : startdatetime.trim();
    }

    public String getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime == null ? null : enddatetime.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }
}