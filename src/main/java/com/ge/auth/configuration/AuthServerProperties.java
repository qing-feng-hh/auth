package com.ge.auth.configuration;

import com.ge.auth.bean.AuthClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: hehui
 * Date: 2019/2/21
 * @Description:
 */
@ConfigurationProperties(prefix = "com.auth.server")
public class AuthServerProperties {

    Logger logger= LoggerFactory.getLogger(AuthServerProperties.class);

    private List<AuthClient> client;

    public List<AuthClient> getClient() {
        return client;
    }

    public void setClient(List<AuthClient> client) {
        this.client = client;
    }
}
