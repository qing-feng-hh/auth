package com.ge.auth.web;

import com.ge.auth.bean.AuthUser;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hehui
 * Date: 2019/3/18
 * @Description:
 */
@RestController
public class TestController {

    @GetMapping("/admin/get")
    public String getAdmin(){
        return "admin";
    }


    @GetMapping("/user/get")
    public String getUser(){
        return "user";
    }


    @GetMapping("/test/get")
    public String getTest(){
        return "test";
    }


    @RequestMapping("/getUserInfo")
    public Object getOauthUserInfo(OAuth2Authentication principal) {
        AuthUser userDetails = (AuthUser) principal.getPrincipal();
//        UserInfoVo userInfoVo = userDetails.get();
        return userDetails;
    }
}
