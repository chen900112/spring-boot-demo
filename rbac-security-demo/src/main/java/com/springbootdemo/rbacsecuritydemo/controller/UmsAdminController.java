package com.springbootdemo.rbacsecuritydemo.controller;

import com.springbootdemo.rbacsecuritydemo.common.api.CommonResult;
import com.springbootdemo.rbacsecuritydemo.dto.UmsAdminLoginDto;
import com.springbootdemo.rbacsecuritydemo.service.UmsAdminService;
import com.springbootdemo.rbacsecuritydemo.vo.UmsUserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginDto umsAdminLoginDto) {
        String userToken =  umsAdminService.login(umsAdminLoginDto);
        return CommonResult.success(userToken);

    }


    @ApiOperation(value = "登录用户")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsUserVo> info() {
        UmsUserVo umsUserVo =  umsAdminService.info();
        return CommonResult.success(umsUserVo);
    }

    @ApiOperation(value = "系统登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
         umsAdminService.logout();
        return CommonResult.success("注销成功");

    }
}
