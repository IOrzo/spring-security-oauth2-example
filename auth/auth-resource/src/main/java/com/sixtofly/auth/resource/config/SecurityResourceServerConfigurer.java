package com.sixtofly.auth.resource.config;

import com.sixtofly.auth.common.config.SecurityAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 资源服务器配置类
 * @author xie yuan bing
 * @date 2019-12-16 11:20
 * @description
 */
public class SecurityResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    private ResourceServerTokenServices tokenServices;

    private OAuth2WebSecurityExpressionHandler expressionHandler;

    private SecurityResourceServerConfigurer(Builder builder) {
        this.tokenServices = builder.tokenServices;
        this.expressionHandler = builder.expressionHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices);


        // 访问异常处理
        resources.authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
        // 拒绝访问处理
//        resources.accessDeniedHandler();

        // TODO 配置oauth2表达式失败
//        resources.expressionHandler(expressionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
        // 配置拦截地址
        http
                .authorizeRequests()
                .antMatchers(
                        "/code/**",
                        "/swagger-ui.html",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/webjars/**"
                ).permitAll()
                // 所有的OPTIONS请求不做校验
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 心跳检测不做校验
                .antMatchers("/instances", "/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
        ;
//        http.authorizeRequests().anyRequest().permitAll();
    }

    public static class Builder {
        private ResourceServerTokenServices tokenServices = null;

        private OAuth2WebSecurityExpressionHandler expressionHandler = null;

        public Builder() {

        }

        public Builder tokenServices(ResourceServerTokenServices tokenServices) {
            this.tokenServices = tokenServices;
            return this;
        }

        public Builder expressionHandler(OAuth2WebSecurityExpressionHandler expressionHandler) {
            this.expressionHandler = expressionHandler;
            return this;
        }



        public SecurityResourceServerConfigurer build() {
            return new SecurityResourceServerConfigurer(this);
        }


    }
}
