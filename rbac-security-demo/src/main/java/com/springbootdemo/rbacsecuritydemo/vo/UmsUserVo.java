package com.springbootdemo.rbacsecuritydemo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springbootdemo.rbacsecuritydemo.po.UmsUserPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_user")
@ApiModel(value = "UmsUserVo")
public class UmsUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

}
