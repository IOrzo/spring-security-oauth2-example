package com.sixtofly.provider.base;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xie yuan bing
 * @date 2020-01-17 16:38
 * @description
 */
public class GeneratePasswordTest {

    @Test
    public void generatePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin"));
    }

}
