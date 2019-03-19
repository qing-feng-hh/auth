package com.ge.auth.service;

import com.ge.auth.bean.AuthUser;

/**
 * @author: hehui
 * Date: 2019/3/16
 * @Description:
 */
public interface AuthUserService {

    /**
     * @param username  用户名称
     * @return
     */
    AuthUser getUserDetail(String username);


}
