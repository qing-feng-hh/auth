package com.ge.auth.config;


import com.ge.auth.bean.AuthClient;
import com.ge.auth.configuration.AuthServerProperties;
import com.ge.auth.exception.CustomWebResponseExceptionTranslator;
import com.ge.auth.token.SimpleUserAuthenticationConverter;
import com.ge.auth.utils.AuthClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.List;

@Configuration
@EnableAuthorizationServer  //  注解开启验证服务器
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthServerProperties authServerProperties;
//

    private static final String CLIEN_ID_ONE = "client_1";  //客户端1 用来标识客户的Id
    private static final String CLIEN_ID_TWO = "client_2";  //客户端2
    private static final String CLIEN_ID_THREE = "client_3";  //客户端3
    private static final String CLIENT_SECRET = "secret";   //secret客户端安全码
    private static final String GRANT_TYPE_PASSWORD = "password";   // 密码模式授权模式
    private static final String AUTHORIZATION_CODE = "authorization_code"; //授权码模式  授权码模式使用到了回调地址，是最为复杂的方式，通常网站中经常出现的微博，qq第三方登录，都会采用这个形式。
    private static final String REFRESH_TOKEN = "refresh_token";  //
    private static final String IMPLICIT = "implicit"; //简化授权模式
    private static final String GRANT_TYPE = "client_credentials";  //客户端模式
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String TRUST = "trust";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;          //
    private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;        //
    private static final String RESOURCE_ID = "*";    //指定哪些资源是需要授权验证的


    Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    @Autowired
    private AuthenticationManager authenticationManager;   //认证方式

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SimpleUserAuthenticationConverter userAuthenticationConverter;  //响应转换器


    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Autowired
    private AuthServerProperties serverProperties;

    @Autowired
    private BCryptPasswordEncoder encoder;



//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

//        logger.info("秘钥是"+secret);
//        //配置3个个客户端,一个用于password认证、一个用于client认证、一个用于authorization_code认证
//        configurer.inMemory()  // 使用in-memory存储
//                .withClient(CLIEN_ID_ONE)    //client_id用来标识客户的Id  客户端1
//                .resourceIds(RESOURCE_ID)
//                .authorizedGrantTypes(GRANT_TYPE, REFRESH_TOKEN)  //允许授权类型   客户端授权模式
//                .scopes(SCOPE_READ,SCOPE_WRITE)  //允许授权范围
//                .authorities("oauth2")  //客户端可以使用的权限
//                .secret(secret)  //secret客户端安全码
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)   //token 时间秒
//                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)//刷新token 时间 秒
//                .and()
//                .withClient(CLIEN_ID_TWO) //client_id用来标识客户的Id  客户端 2
//                .resourceIds(RESOURCE_ID)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, REFRESH_TOKEN)   //允许授权类型  密码授权模式
//                .scopes(SCOPE_READ,SCOPE_WRITE) //允许授权范围
//                .authorities("oauth2") //客户端可以使用的权限
//                .secret(secret)  //secret客户端安全码
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)    //token 时间秒
//                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS); //刷新token 时间 秒
//
//    }


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        //校验过期时间
        List<AuthClient> clients = serverProperties.getClient();
//
        String secret = encoder.encode(CLIENT_SECRET);  // 用 BCrypt 对密码编码

//        String secret=new BCryptPasswordEncoder().encode(CLIENT_SECRET);
//
        InMemoryClientDetailsServiceBuilder builder = configurer.inMemory();


//        for (AuthClient client : clients) {
//            builder.
//        }
//        InMemoryClientDetailsServiceBuilder and = builder.and();
//        private List<ClientBuilder> clientBuilders = new ArrayList<ClientBuilder>();

//
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder oauth2=null;
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> and=null;
        for(int i=0;i<clients.size();i++){
            AuthClient client = clients.get(i);
            if(i==0){
                oauth2= builder
                        .withClient(client.getClient_id())
                        .secret(encoder.encode(client.getClient_secret()))
                        .resourceIds(RESOURCE_ID)
                        .authorizedGrantTypes(client.getGrant_type())
                        .scopes(SCOPE_READ, SCOPE_WRITE)  //允许授权范围
                        .authorities("oauth2")
                        .accessTokenValiditySeconds(client.getAccess_token_timeout())
                        .refreshTokenValiditySeconds(client.getRefresh_token_timeout());
            }else{
                //第二次及后续几次
                and
                        .withClient(client.getClient_id())
                        .secret(encoder.encode(client.getClient_secret()))
                        .resourceIds(RESOURCE_ID)
                        .authorizedGrantTypes(client.getGrant_type())
                        .scopes(SCOPE_READ, SCOPE_WRITE)  //允许授权范围
                        .authorities("oauth2")
                        .accessTokenValiditySeconds(client.getAccess_token_timeout())
                        .refreshTokenValiditySeconds(client.getRefresh_token_timeout());
            }

            if(i!=clients.size()-1){
                 and = oauth2.and();
            }
        }

//
//        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder oauth2 =
//                and;
//                builder.withClient(CLIEN_ID_ONE)    //client_id用来标识客户的Id  客户端1
//                .resourceIds(RESOURCE_ID)
//                .authorizedGrantTypes(GRANT_TYPE, REFRESH_TOKEN)  //允许授权类型   客户端授权模式
//                .scopes(SCOPE_READ, SCOPE_WRITE)  //允许授权范围
//                .authorities("oauth2")  //客户端可以使用的权限
//                .secret(secret)  //secret客户端安全码
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)   //token 时间秒
//                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
//
//        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> and = oauth2.and();//
////
//        and //第二个客户端
//                .withClient(CLIEN_ID_TWO) //client_id用来标识客户的Id  客户端 2
//                .resourceIds(RESOURCE_ID)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, REFRESH_TOKEN)   //允许授权类型  密码授权模式
//                .scopes(SCOPE_READ,SCOPE_WRITE) //允许授权范围
//                .authorities("oauth2") //客户端可以使用的权限
//                .secret(secret)  //secret客户端安全码 //
//                .accessTokenValiditySeconds(3600)    //token 时间秒
//                .refreshTokenValiditySeconds(3600); //刷新token 时间 秒

//        for (AuthClient client : clients) {
////            buildClient(builder,client);
//            System.out.println(client.getClient_id());
//
//        }
//        String secret = new BCryptPasswordEncoder().encode(CLIENT_SECRET);
//
//        configurer.inMemory()
//       // 使用in-memory存储
//                .withClient(CLIEN_ID_ONE)    //client_id用来标识客户的Id  客户端1
//                .resourceIds(RESOURCE_ID)
//                .authorizedGrantTypes(GRANT_TYPE, REFRESH_TOKEN)  //允许授权类型   客户端授权模式
//                .scopes(SCOPE_READ,SCOPE_WRITE)  //允许授权范围
//                .authorities("oauth2")  //客户端可以使用的权限
//                .secret(secret)  //secret客户端安全码
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)   //token 时间秒
//                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)//刷新token 时间 秒
//                .and()
//                //第二个客户端
//                .withClient(CLIEN_ID_TWO) //client_id用来标识客户的Id  客户端 2
//                .resourceIds(RESOURCE_ID)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, REFRESH_TOKEN)   //允许授权类型  密码授权模式
//                .scopes(SCOPE_READ,SCOPE_WRITE) //允许授权范围
//                .authorities("oauth2") //客户端可以使用的权限
//                .secret(secret)  //secret客户端安全码 //
//                .accessTokenValiditySeconds(3600)    //token 时间秒
//                .refreshTokenValiditySeconds(3600); //刷新token 时间 秒
    }



    /**
     * 指定
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置TokenServices参数
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setSupportRefreshToken(false);
        defaultTokenServices.setTokenStore(tokenStore());

        defaultTokenServices.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
//        //若通过 JDBC 存储令牌
//        if (Objects.nonNull(jdbcClientDetailsService)){
//            defaultTokenServices.setClientDetailsService(jdbcClientDetailsService);
//        }
        DefaultAccessTokenConverter defaultAccessTokenConverter=new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);

        endpoints.authenticationManager(authenticationManager)  //登录失败处理
                .tokenStore(tokenStore())       //支持GET  POST  请求获取token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(defaultAccessTokenConverter)
//                .tokenServices(defaultTokenServices);//添加登录失败异常转换器
                .tokenServices(defaultTokenServices)
                .exceptionTranslator(customWebResponseExceptionTranslator);
    }

    /**
     * 认证服务器的安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //isAuthenticated():排除anonymous   isFullyAuthenticated():排除anonymous以及remember-me
                .allowFormAuthenticationForClients();  //允许表单认证
    }


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
        //基于jwt实现令牌（Access Token）
//      return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public ResourceServerTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}
