package com.sixtofly.application.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author
 */
@Configuration
public class WebConfig {

    @Bean
    public MethodValidationPostProcessor mvcMethodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
