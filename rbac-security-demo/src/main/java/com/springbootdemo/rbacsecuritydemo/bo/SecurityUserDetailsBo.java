package com.springbootdemo.rbacsecuritydemo.bo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springbootdemo.rbacsecuritydemo.po.UmsPermissionPo;
import com.springbootdemo.rbacsecuritydemo.po.UmsUserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SecurityUserDetailsBo implements UserDetails {

    // 接收数据返回的用户 PO ,login 过程中在 loadUserByUsername 中设置
    private UmsUserPo umsUserPo;

    // 接收数据返回的权限 PoList ,login 过程中在 loadUserByUsername 中设置
    private List<UmsPermissionPo> umsPermissionPoList;


    // 提供给 Security 鉴权, @JsonIgnore:在redis序列化中忽略
    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;


    public SecurityUserDetailsBo(UmsUserPo umsUserPo, List<UmsPermissionPo> umsPermissionPoList) {
        this.umsUserPo = umsUserPo;
        this.umsPermissionPoList = umsPermissionPoList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // jwt 鉴权过中,如果已经存在则会直接反,这里是一个优化，一个线程中你只登录的第一次需要
        if (Objects.nonNull(authorities)){
            return  authorities;
        }

        // umsPermissionPoList => authorities
        return umsPermissionPoList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getPermission()))
                .collect(Collectors.toList());

    }

    // 返回登录用户账户名和密码 用于Security判断
    @Override
    public String getPassword() {
        return umsUserPo.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUserPo.getUserName();
    }


    // 提供了一些配置方案

    // 账户没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户没有被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 身份认证是否是有效的
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
