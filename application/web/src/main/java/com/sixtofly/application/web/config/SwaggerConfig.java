package com.sixtofly.application.web.config;

import com.github.xiaoymin.swaggerbootstrapui.filter.ProductionSecurityFilter;
import com.github.xiaoymin.swaggerbootstrapui.filter.SecurityBasicAuthFilter;
import com.sixtofly.application.web.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置类
 * @author xie yuan bing
 * @date 2020-01-19 10:07
 * @description
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties properties;

    @Bean
    public Docket api(){
        final List<Parameter> parameters = new ArrayList<>();
        // 参数设置
        final Parameter token = new ParameterBuilder()
                .name("Authorization")
                .description("token")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                // 全局参数
                .globalOperationParameters(parameters)
                // swagger开关
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置swagger界面登录校验
     * @return
     */
    @Bean
    public FilterRegistrationBean<SecurityBasicAuthFilter> securityBasicAuthFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SecurityBasicAuthFilter(properties.isEnable(), properties.getUsername(), properties.getPassword()));
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    /**
     * 接口信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("API标题")
                // API简介
                .description("API介绍")
                // 接口地址
                .termsOfServiceUrl("http://127.0.0.1")
                .version("1.0")
                .build();
    }

}
