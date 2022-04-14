package com.fourzhang.youddit.config;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("privateMsg");
    }
}
