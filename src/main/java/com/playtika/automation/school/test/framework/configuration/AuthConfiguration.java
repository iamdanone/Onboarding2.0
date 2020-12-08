package com.playtika.automation.school.test.framework.configuration;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.client.AuthFeignClient;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = AuthFeignClient.class)
@Configuration
@EnableAutoConfiguration
public class AuthConfiguration {

    @Bean
    public AuthActions authActions(AuthFeignClient client) {
        return new AuthActions(client);
    }
}