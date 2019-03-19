package com.ge.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author: hehui
 * Date: 2019/2/27
 * @Description:    自定义oauth2异常
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)    //指定自定义序列化
public class CustomOauthException extends OAuth2Exception {

    public CustomOauthException(String msg) {
        super(msg);
    }
}
