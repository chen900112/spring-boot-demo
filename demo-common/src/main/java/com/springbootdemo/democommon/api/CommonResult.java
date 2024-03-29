package com.springbootdemo.democommon.api;


/**
 * 通用返回结果封装类
 * Created by macro on 2019/4/19.
 */
public class CommonResult<T> {
    /**
     * 状态码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T payload;

    protected CommonResult() {
    }

    protected CommonResult(String code, String message, T payload) {
        this.code = code;
        this.message = message;
        this.payload = payload;
    }
    /**
     * 成功返回结果
     *
     * @param payload 获取的数据
     */
    public static <T> CommonResult<T> success(T payload) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), payload);
    }

    /**
     * 成功返回结果
     *
     * @param payload 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T payload, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, payload);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IResultCode resultCode) {
        return new CommonResult<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> CommonResult<T> failed(IResultCode errorCode, String message) {
        return new CommonResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T payload) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), payload);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T payload) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), payload);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getpayload() {
        return payload;
    }

    public void setpayload(T payload) {
        this.payload = payload;
    }
}
