package com.springbootdemo.rbacsecuritydemo.common.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredJwtException extends AuthenticationException {
    public ExpiredJwtException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ExpiredJwtException(String msg) {
        super(msg);
    }
}
