package com.springbootdemo.rbacsecuritydemo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
@Data
public class UmsAdminLoginDto {
    @NotEmpty(message="用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @NotEmpty(message="密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
