package com.springbootdemo.rbacsecuritydemo.common.security;

import org.springframework.stereotype.Component;

/**
 * @PreAuthorize("@sp.hasAuthority('employee:menu:one')")
 * public CommonResult menu02() {
 *     return CommonResult.success();
 *
 * }
 */

// 注入到容器
@Component("sp")
public class SecurityPermission {

    public boolean hasAuthority(){
        // 需要的鉴权逻辑
        // ....
        return true;
    }
}
