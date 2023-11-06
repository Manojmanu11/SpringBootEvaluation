package com.example.SpringEvaluation.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private final String  username;
    private final String password;

    private final List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserDetail userDetail) {            //set userDetails using username
        username=userDetail.getUserName();
        password=userDetail.getPassword();
        authorities= Arrays.stream(userDetail.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public UserInfoUserDetails(UserDetail userDetail, boolean useEmailAsUsername) {     //set userDetails using email as username
        if (useEmailAsUsername) {
            this.username = userDetail.getEmail();
        } else {
            this.username = userDetail.getUserName();
        }
        this.password = userDetail.getPassword();
        this.authorities = Arrays.stream(userDetail.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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