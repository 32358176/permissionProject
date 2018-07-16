package com.crm.interceptor.exception;

public class NoPermissionException extends Exception {
    public NoPermissionException() {
        super("无权访问");
    }

    public NoPermissionException(String message) {
        super(message);
    }
}
