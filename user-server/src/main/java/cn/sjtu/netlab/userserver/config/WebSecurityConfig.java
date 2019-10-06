package cn.sjtu.netlab.userserver.config;

import cn.sjtu.netlab.userserver.security.LabLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("detail")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/api-docs/**").permitAll();
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                // 跨域预检请求
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                // oauth授权放行
//                .antMatchers("/oauth/**").permitAll()
//                // 自定义验证码路径
//                .antMatchers("/captcha").permitAll()
//                .and().authorizeRequests().anyRequest().authenticated()
//                // 自定义登录页和登录请求路径
//                .and().formLogin().loginPage("/loginS.html").loginProcessingUrl("/loginS").permitAll()
//                // 登出
//                .and().logout().logoutSuccessHandler(new LabLogoutHandler());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
