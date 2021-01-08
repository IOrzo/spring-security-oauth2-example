package com.sixtofly.auth.server.config;

import com.sixtofly.auth.common.config.SecurityAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 授权配置类
 * @author xie yuan bing
 * @date 2019-12-11 14:43
 * @description
 */
@Configuration
public class SecurityWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                // 自定义登录界面
                .loginPage("/login.html")
                // 自定义登录请求地址
                .loginProcessingUrl("/login")
                .and()
                .httpBasic()
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/actuator/**",
                        "/oauth/**",
                        "/token/**",
                        "/login",
                        "/login.html"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                // JWT令牌时禁用session(注: 要使用授权码模式时不能禁用session)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                // 禁用 Spring Security 自带的跨域处理
                .csrf().disable();


//        http
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .csrf().disable();
    }
}
