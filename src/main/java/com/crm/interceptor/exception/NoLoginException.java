package com.crm.interceptor.exception;

public class NoLoginException extends Exception{
    public NoLoginException() {
        super("没有登陆");
    }

    public NoLoginException(String message) {
        super(message);
    }
}
