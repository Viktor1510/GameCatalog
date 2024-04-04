package com.example.gamecatalog.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Logger logger() {
        return LogManager.getLogger(); // Or any method to initialize your logger
    }
}
