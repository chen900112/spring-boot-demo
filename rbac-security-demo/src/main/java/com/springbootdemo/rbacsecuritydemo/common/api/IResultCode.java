package com.springbootdemo.rbacsecuritydemo.common.api;

/**
 * API返回码接口
 * Created by macro on 2019/4/19.
 */
public interface IResultCode {
    /**
     * 返回码
     */
    String getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
