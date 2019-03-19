package com.ge.auth.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: hehui
 * Date: 2019/2/26
 * @Description:    自定义转换器用于响应全部用户信息
 */
@Configuration
public class SimpleUserAuthenticationConverter extends DefaultUserAuthenticationConverter {



    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap();
        response.put("USERNAME", authentication);
        return response;
    }

}
