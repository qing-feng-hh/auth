package com.ge.auth.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hehui
 * Date: 2019/2/22
 * @Description:
 */
@Configuration
@EnableConfigurationProperties({AuthResourceProperties.class,AuthServerProperties.class})
@ConditionalOnProperty(prefix="com.auth",value="enabled",matchIfMissing = true)
public class AuthConfiguration {

}
