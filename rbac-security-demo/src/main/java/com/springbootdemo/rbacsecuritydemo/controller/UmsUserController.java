package com.springbootdemo.rbacsecuritydemo.controller;

import com.springbootdemo.rbacsecuritydemo.common.api.CommonResult;
import com.springbootdemo.rbacsecuritydemo.dto.UmsAdminLoginDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@RestController
@RequestMapping("/umsUser")

public class UmsUserController {

    @ApiOperation(value = "管理菜单")
    @RequestMapping(value = "/menu01", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin:menu:one')")
    @ResponseBody
    public CommonResult menu01() {

        return CommonResult.success("管理菜单");

    }

    @ApiOperation(value = "员工菜单")
    @RequestMapping(value = "/menu02", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('employee:menu:one')")
    @ResponseBody
    public CommonResult menu02() {

        return CommonResult.success("员工菜单");

    }
}
