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

    private UmsUserPo umsUserPo;

    private List<UmsPermissionPo> umsPermissionPoList;


    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;


    public SecurityUserDetailsBo(UmsUserPo umsUserPo, List<UmsPermissionPo> umsPermissionPoList) {
        this.umsUserPo = umsUserPo;
        this.umsPermissionPoList = umsPermissionPoList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (Objects.nonNull(authorities)){
            return  authorities;
        }

        return umsPermissionPoList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getPermission()))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return umsUserPo.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUserPo.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
