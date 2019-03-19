package com.ge.auth.endpoint;

import com.ge.auth.bean.AuthResponsePayload;
import com.ge.auth.bean.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hehui
 * Date: 2019/2/26
 * @Description:
 */
@RestController
public class AuthEndPointer {

    Logger logger = LoggerFactory.getLogger(AuthEndPointer.class);

    /**
     *客户端根据token获取用户
     */
    @GetMapping("/auth/getUser")
    public AuthResponsePayload getAuthUser(OAuth2Authentication principal) {
        Object user = principal.getPrincipal();
        if(user==null){
            logger.info("=========无法根据token找到用户信息=========");
            throw new NullPointerException();
        }
        if(user instanceof AuthUser ){
            AuthUser authUser= (AuthUser) user;
            AuthResponsePayload payload = new AuthResponsePayload();
            payload.setAuthorities(authUser.getAuthorities());
            payload.setId(authUser.getId());
            payload.setUsername(authUser.getUsername());
            BeanUtils.copyProperties(payload,authUser);
            logger.info("token的用户信息是"+payload);
            return  payload;
        }
        logger.info("=========反序列化失败=========");
        throw new SerializationFailedException("");
    }


}
