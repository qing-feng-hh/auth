package com.ge.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: hehui
 * Date: 2019/2/27
 * @Description:    登录失败异常处理转换器
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    Logger logger = LoggerFactory.getLogger(CustomWebResponseExceptionTranslator.class);

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;

        int errorStatus = HttpServletResponse.SC_UNAUTHORIZED;//401
        logger.info("=================登录失败========================");
        ResponseEntity.status(errorStatus);
        CustomOauthException exception = new CustomOauthException(oAuth2Exception.getMessage());
        return ResponseEntity.status(errorStatus).body(exception);
    }
}
