package com.springbootdemo.rbacsecuritydemo.mapper;

import com.springbootdemo.rbacsecuritydemo.po.UmsPermissionPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermissionPo> {

    List<UmsPermissionPo> getPermissionList(@Param("userId") Long userId);
}
