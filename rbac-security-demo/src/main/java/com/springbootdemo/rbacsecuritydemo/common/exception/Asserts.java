package com.springbootdemo.rbacsecuritydemo.common.exception;

import com.springbootdemo.rbacsecuritydemo.common.api.IResultCode;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by macro on 2020/2/27.
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IResultCode errorCode) {
        throw new ApiException(errorCode);
    }
}
