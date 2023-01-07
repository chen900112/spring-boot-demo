package com.springbootdemo.rbacsecuritydemo.common.security;

import com.springbootdemo.rbacsecuritydemo.bo.SecurityUserDetailsBo;
import com.springbootdemo.rbacsecuritydemo.common.exception.ExpiredJwtException;
import com.springbootdemo.rbacsecuritydemo.common.jwt.JwtUtil;
import com.springbootdemo.rbacsecuritydemo.common.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.toKenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     try {
         String authHeader = request.getHeader(this.tokenHeader);
         if(StringUtils.hasText(authHeader) && authHeader.startsWith(this.tokenHead)){
             String authToken = authHeader.substring(this.tokenHead.length()).trim();
             // 获取jwt中的username
             String username = jwtUtil.getUserNameFromToken(authToken);

             if ( !Objects.isNull(username) && SecurityContextHolder.getContext().getAuthentication() == null){
                 // 检验 jwt 是否生效
                 if(jwtUtil.validateToken(authToken,username)){
                     // 通过 jwt 中的username 拼接redis key 获取用户信息
                     String key = REDIS_DATABASE  + ":" + username;

                     SecurityUserDetailsBo securityUserDetailsBo = (SecurityUserDetailsBo) redisUtil.get(key);
                     //  从 redis 中获取数据
                     if(!Objects.isNull(securityUserDetailsBo)  ){
                         UsernamePasswordAuthenticationToken authenticationToken =
                                 new UsernamePasswordAuthenticationToken(securityUserDetailsBo,null,securityUserDetailsBo.getAuthorities());

                         SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                     }else {
                         throw new ExpiredJwtException("用户未登录");
                     }
                 } else {
                     throw new ExpiredJwtException("验证token异常");
                 }
             }else {
                 throw new ExpiredJwtException("token 异常");
             }
         }
         filterChain.doFilter(request,response);
     }catch (AuthenticationException ex){
         SecurityContextHolder.clearContext();
         this.restAuthenticationEntryPoint.commence(request, response, ex);
     }
    }
}
