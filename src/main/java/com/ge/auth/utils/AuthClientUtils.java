package com.ge.auth.utils;

import org.springframework.util.StringUtils;

/**
 * @author: hehui
 * Date: 2019/3/18
 * @Description:
 */
public class AuthClientUtils {


    public static boolean isClientIdVaild(String clientId){
        if(StringUtils.isEmpty(clientId)){
            System.out.println("client id is null");
            throw new NullPointerException("");
        }
        return true;
    }

    public static boolean isClientSecretVaild(String clientId){
        if(StringUtils.isEmpty(clientId)){
            System.out.println("client secret is null");
           throw new NullPointerException("");
        }
        return true;
    }

    public static boolean isGrantTypeVaild(String grantType){
        if(StringUtils.isEmpty(grantType)){
            System.out.println("grant type is null");
            throw new NullPointerException("");
        }
        switch(grantType){
            case "password":
            case "authorization_code":
            case "implicit":
            case "client_credentials":
                return true;
            default:
                throw new IllegalArgumentException("grant type is not satisfactory");
        }
    }



}
