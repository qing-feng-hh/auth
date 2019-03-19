package com.ge.auth.configuration;

import com.ge.auth.bean.AuthRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: hehui
 * Date: 2019/2/21
 * @Description: 资源服务配置类
 */
@ConfigurationProperties(prefix = "com.auth.resource")
public class AuthResourceProperties {

    Logger logger= LoggerFactory.getLogger(AuthResourceProperties.class);

    private List<AuthRoute> route;


    public List<AuthRoute> getRoute() {
        return route;
    }

    public void setRoute(List<AuthRoute> route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "AuthResourceProperties{" +
                "route=" + route +
                '}';
    }
}
