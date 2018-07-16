package com.crm.pojo;

import java.io.Serializable;

public class Roles implements Serializable {
    private Integer id;

    private String name;

    private Integer int0;

    private String string0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getInt0() {
        return int0;
    }

    public void setInt0(Integer int0) {
        this.int0 = int0;
    }

    public String getString0() {
        return string0;
    }

    public void setString0(String string0) {
        this.string0 = string0 == null ? null : string0.trim();
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", int0=" + int0 +
                ", string0='" + string0 + '\'' +
                '}';
    }
}