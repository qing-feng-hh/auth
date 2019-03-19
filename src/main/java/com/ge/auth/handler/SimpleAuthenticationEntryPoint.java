package com.ge.auth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token校验失败处理   402
 */
@Component
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {


	Logger logger = LoggerFactory.getLogger(SimpleAuthenticationEntryPoint.class);



    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.info("=================token校验失败========================");
        response.setStatus(HttpServletResponse.SC_PAYMENT_REQUIRED);
    }
}
