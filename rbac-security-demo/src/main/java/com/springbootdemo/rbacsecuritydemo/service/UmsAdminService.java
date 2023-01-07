package com.springbootdemo.rbacsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootdemo.rbacsecuritydemo.bo.SecurityUserDetailsBo;
import com.springbootdemo.rbacsecuritydemo.dto.UmsAdminLoginDto;
import com.springbootdemo.rbacsecuritydemo.po.UmsUserPo;
import com.springbootdemo.rbacsecuritydemo.vo.UmsUserVo;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
public interface UmsAdminService extends IService<UmsUserPo> {

    String login(UmsAdminLoginDto umsAdminLoginDto);

    UmsUserVo info();

    void logout();
}
