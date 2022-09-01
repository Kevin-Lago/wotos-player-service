package com.wotos.wotosplayerservice.config;

import feign.Feign;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EnvironmentConfig {

    @Bean
    public Feign.Builder WotApi() {
        return Feign.builder().contract(new SpringMvcContract());
    }

}
