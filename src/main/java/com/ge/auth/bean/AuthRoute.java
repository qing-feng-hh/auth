package com.ge.auth.bean;

/**
 * @author: hehui
 * Date: 2019/3/18
 * @Description:
 */
public class AuthRoute {

    private String pattern;
    private String role;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthRoute{" +
                "pattern='" + pattern + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
