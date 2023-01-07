package com.springbootdemo.rbacsecuritydemo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@Getter
@Setter
@TableName("ums_permission")
@ApiModel(value = "UmsPermissionPo对象", description = "权限表")
public class UmsPermissionPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;

    @ApiModelProperty("权限名")
    private String permissionName;

    @ApiModelProperty("权限类型（M菜单 C控制器）")
    private String permissionType;

    @ApiModelProperty("权限表达式")
    private String permission;


}
