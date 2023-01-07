package com.springbootdemo.rbacsecuritydemo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@Getter
@Setter
@TableName("ums_role_permission")
@ApiModel(value = "UmsRolePermissionPo对象", description = "角色权限关系表")
public class UmsRolePermissionPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色主键")
    private Long roleId;

    @ApiModelProperty("权限主键")
    private Long permissionId;


}
