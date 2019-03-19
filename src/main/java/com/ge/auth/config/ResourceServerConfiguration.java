package com.ge.auth.config;


import com.ge.auth.bean.AuthRoute;
import com.ge.auth.configuration.AuthResourceProperties;
import com.ge.auth.handler.SimpleAuthenticationEntryPoint;
import com.ge.auth.handler.SimpleLogoutSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.List;

@Configuration
@EnableResourceServer
public  class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "*";

    @Bean
    public LogoutSuccessHandler customLogoutSuccessHandler()
    {
        return new SimpleLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint(){
        return new SimpleAuthenticationEntryPoint();
    }


    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;    //

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthResourceProperties authResourceProperties;



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        resources.resourceId(RESOURCE_ID).stateless(true).tokenServices(tokenServices);

    }


    @Autowired
    private AuthResourceProperties resourceProperties;


    Logger logger= LoggerFactory.getLogger(ResourceServerConfiguration.class);

    @Override
    public  void configure(HttpSecurity http)
 throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/test/**").hasRole("TEST")
////                .antMatchers("/users/**").authenticated() //配置users访问控制，必须认证过后才可以访问
//                .antMatchers("/**").authenticated() //配置访问控制，必须认证过后才可以访问
//                //.antMatchers("/**").permitAll()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint())  //认证失败的业务处理
//                .and()
//                .logout()
//                .logoutUrl("/oauth/logout")
//                .logoutSuccessHandler(customLogoutSuccessHandler());   //退出成功的业务处理
    //隔离

        List<AuthRoute> routes = resourceProperties.getRoute();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();

//        registry.antMatchers("/oauth/**").permitAll();
        //放行oauth2
        for (AuthRoute route : routes) {
            String pattern = route.getPattern();
            String role = route.getRole();
            logger.info(pattern);//
            registry.antMatchers(pattern).hasRole(role);
        }



//        http.authorizeRequests().antMatchers("/**").authenticated()
        registry.antMatchers("/**").authenticated()


////                .antMatchers("/users/**").authenticated() //配置users访问控制，必须认证过后才可以访问
//        antMatchers("/**").authenticated() //配置访问控制，必须认证过后才可以访问
                //.antMatchers("/**").permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint())  //认证失败的业务处理
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler());   //退出成功的业务处理
    }


}
