package com.crm.utils;


public enum TYPE {
    ORACLE("oracle"),
    MYSQL("mysql"),
    SQL_SERVER("sql_server");

    private String type = "mysql";

    private TYPE(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
