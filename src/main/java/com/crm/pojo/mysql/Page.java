package com.crm.pojo.mysql;

import com.github.pagehelper.PageInfo;


public class Page {
    Integer code;
    String msg;
    Long count;
    Object data;

    public Page(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Page(Object data) {
        this.data = data;
    }

    public Page(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Page(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Page(PageInfo pageInfo) {
        this.code = 0;
        this.msg = "成功";
        this.count = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
