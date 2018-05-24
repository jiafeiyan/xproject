package com.zeus.boot.commons.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private int status = 500;

    @Getter
    private String code;

    public BusinessException(String message) {
        this(message, 500);
    }

    public BusinessException(String message, String code) {
        this(message, code, 500);
    }

    public BusinessException(String message, String code, int status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public BusinessException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }
}
