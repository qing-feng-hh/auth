//package com.ge.auth.service.impl;
//
//import com.ge.auth.bean.AuthUser;
//import com.ge.auth.service.AuthUserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
///**
// * @author: hehui
// * Date: 2019/2/15
// * @Description: 登录认证
// */
//@Service
//public class PasswordUserDetailsService implements UserDetailsService {
//
//
//    @Autowired
//    private AuthUserService userService;
//
//
//    Logger logger = LoggerFactory.getLogger(PasswordUserDetailsService.class);
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AuthUser user=null;
//        if(!StringUtils.isEmpty(username)){
//            user = userService.getUserDetail(username);
//            logger.info("用户信息是"+user);
//            if(user==null){
//                logger.info("The user obtained by username  does not exist");
//                throw  new UsernameNotFoundException("");
//            }
//        }
//
//        return user;
//    }
//}
