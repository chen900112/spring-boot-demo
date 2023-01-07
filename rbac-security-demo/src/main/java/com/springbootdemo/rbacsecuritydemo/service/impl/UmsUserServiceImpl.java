package com.springbootdemo.rbacsecuritydemo.service.impl;

import com.springbootdemo.rbacsecuritydemo.po.UmsUserPo;
import com.springbootdemo.rbacsecuritydemo.mapper.UmsUserMapper;
import com.springbootdemo.rbacsecuritydemo.service.UmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUserPo> implements UmsUserService {


}
