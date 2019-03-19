package com.ge.auth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: hehui
 * Date: 2019/2/27
 * @Description:    权限不足处理 403
 */
@Component
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

    Logger logger = LoggerFactory.getLogger(SimpleAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        logger.info("=================权限不足======================== ");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
