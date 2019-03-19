package com.ge.auth.service.impl;

import com.ge.auth.bean.AuthUser;
import com.ge.auth.service.AuthUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author: hehui
 * Date: 2019/3/18
 * @Description:
 */

@Service
public class AuthUserServiceImpl implements AuthUserService {


    @Override
    public AuthUser getUserDetail(String username) {

        //模拟从数据源获取用户信息
        AuthUser user = new AuthUser();
        user.setUsername(username);//设置用户名及密码
        user.setPassword("$2a$10$vor2j1wCcX/cVkxtDYpJ9OXoDpT8dx14oOg4bADRR05SzgSX1LRDi");
        HashSet set = new HashSet();

        //设置权限
        if("test".equals(username)){
            set.add(new SimpleGrantedAuthority("ROLE_TEST"));
        }
        if("user".equals(username)){
            set.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        if("admin".equals(username)){
            set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        user.setAuthorities(set);
        user.setId("1");
        return user;
    }
}
