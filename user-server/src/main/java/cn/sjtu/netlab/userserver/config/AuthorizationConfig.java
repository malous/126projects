package cn.sjtu.netlab.userserver.config;

import cn.sjtu.netlab.userserver.security.JwtAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Qualifier("detail")
    @Autowired
    private UserDetailsService userDetailsService;

    //    @Autowired
//    private RedisConnectionFactory connectionFactory;
//
//    public RedisTokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }
//    private TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
    private ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService());
//        clients.jdbc(dataSource); //功能与上一句相同
        clients.inMemory()
                .withClient("net_lab")
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("web")
                .redirectUris("https://www.baidu.com");
//                .autoApprove("*");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
//                .tokenStore(tokenStore())
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()")
//                .passwordEncoder(new BCryptPasswordEncoder());
        security.passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
//        super.configure(security);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessToken();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "overturn".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("firstKey"));
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }
}
