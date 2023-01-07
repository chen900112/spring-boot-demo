package com.springbootdemo.rbacsecuritydemo.common.exception;


import com.springbootdemo.rbacsecuritydemo.common.api.IResultCode;

/**
 * 自定义API异常
 * Created by macro on 2020/2/27.
 */
public class ApiException extends RuntimeException {
    
    private IResultCode resultCode;

    public ApiException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IResultCode getErrorCode() {
        return resultCode;
    }
}
