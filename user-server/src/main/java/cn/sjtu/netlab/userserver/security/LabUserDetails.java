package cn.sjtu.netlab.userserver.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class LabUserDetails implements UserDetails {
    private String username;
    private String password;
    private Boolean nonLocked;
    private Boolean nonDeleted;
    private Set<? extends GrantedAuthority> authorities;

    public LabUserDetails(String username, String password, Boolean nonLocked, Boolean nonDeleted, Set<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.nonLocked = nonLocked;
        this.nonDeleted = nonDeleted;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return nonDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
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
