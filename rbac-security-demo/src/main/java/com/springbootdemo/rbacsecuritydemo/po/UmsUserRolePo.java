package com.springbootdemo.rbacsecuritydemo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@Getter
@Setter
@TableName("ums_user_role")
@ApiModel(value = "UmsUserRolePo对象", description = "用户角色关系表")
public class UmsUserRolePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户主键")
    private Long userId;

    @ApiModelProperty("角色主键")
    private Long roleId;


}
