package com.springbootdemo.rbacsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootdemo.rbacsecuritydemo.bo.SecurityUserDetailsBo;
import com.springbootdemo.rbacsecuritydemo.common.jwt.JwtUtil;
import com.springbootdemo.rbacsecuritydemo.common.redis.RedisUtil;
import com.springbootdemo.rbacsecuritydemo.dto.UmsAdminLoginDto;
import com.springbootdemo.rbacsecuritydemo.mapper.UmsAdminMapper;
import com.springbootdemo.rbacsecuritydemo.mapper.UmsPermissionMapper;
import com.springbootdemo.rbacsecuritydemo.po.UmsPermissionPo;
import com.springbootdemo.rbacsecuritydemo.po.UmsUserPo;
import com.springbootdemo.rbacsecuritydemo.service.UmsAdminService;
import com.springbootdemo.rbacsecuritydemo.vo.UmsUserVo;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2023-01-01
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsUserPo> implements UmsAdminService, UserDetailsService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UmsPermissionMapper umsPermissionMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire}")
    private Long REDIS_EXPIRE;

    @Override
    public String login(UmsAdminLoginDto umsAdminLoginDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(umsAdminLoginDto.getUserName(),umsAdminLoginDto.getPassword());
        Authentication authentication =  authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }
        SecurityUserDetailsBo securityUserDetailsBo = (SecurityUserDetailsBo) authentication.getPrincipal();
        String userFlag = securityUserDetailsBo.getUsername().toString();
        String userToken = jwtUtil.generateToken(userFlag);

        String key = REDIS_DATABASE  + ":" + securityUserDetailsBo.getUsername();

        // token 存储入 redis
        redisUtil.set(key,securityUserDetailsBo,REDIS_EXPIRE);

        return userToken;
    }

    @Override
    public UmsUserVo info() {

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        SecurityUserDetailsBo securityUserDetailsBo = (SecurityUserDetailsBo) auth.getPrincipal();

        LambdaQueryWrapper<UmsUserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUserPo::getUserName,securityUserDetailsBo.getUsername());
        UmsUserPo umsUserPo  =  this.getOne(queryWrapper);

//        UmsUserVo umsUserVo = new UmsUserVo();

        Mapper dozerMapper = new DozerBeanMapper();
        UmsUserVo umsUserVo = dozerMapper.map(umsUserPo, UmsUserVo.class);

        return umsUserVo;
    }

    @Override
    public void logout() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        SecurityUserDetailsBo securityUserDetailsBo = (SecurityUserDetailsBo) auth.getPrincipal();
        String key = REDIS_DATABASE  + ":" + securityUserDetailsBo.getUsername();

        redisUtil.del(key);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<UmsUserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUserPo::getUserName,username);
        UmsUserPo umsUserPo  =  this.getOne(queryWrapper);

        if(Objects.isNull(umsUserPo)){
            throw new RuntimeException("错误啦");
        }

        List<UmsPermissionPo> permissionList = umsPermissionMapper.getPermissionList(umsUserPo.getUserId());

        return new SecurityUserDetailsBo(umsUserPo,permissionList);
    }


}
