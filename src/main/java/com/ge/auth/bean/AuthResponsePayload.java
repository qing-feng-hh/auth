package com.ge.auth.bean;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * @author: hehui
 * Date: 2019/3/18
 * @Description:
 */
public class AuthResponsePayload{

    /**
     * 用户编号
     */
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     *用户所拥有的权限
     */
    private Set<? extends GrantedAuthority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "AuthResponsePayload{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
