package com.ge.auth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理注销
 */
public class SimpleLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	Logger logger = LoggerFactory.getLogger(SimpleLogoutSuccessHandler.class);

    @Autowired
    private TokenStore tokenStore ;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String access_token = request.getParameter("access_token");
        if(access_token != null){
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
            logger.info("token =" +oAuth2AccessToken.getValue());
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        logger.info("=================注销成功========================");
    }
}
