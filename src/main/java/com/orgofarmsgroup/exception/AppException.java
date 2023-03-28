package com.orgofarmsgroup.exception;

public class AppException extends RuntimeException{
    private final String errorCode;
    public AppException(String message) {
        super(message);
        this.errorCode = "CRSA-0000";
    }

    public AppException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(Throwable throwable) {
        super(throwable);
        this.errorCode = "CRSA-0001";
    }
}
