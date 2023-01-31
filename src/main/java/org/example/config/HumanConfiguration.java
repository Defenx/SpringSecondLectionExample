package org.example.config;

import org.example.dto.Human;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HumanConfiguration {

    @Bean
    public Human getHuman() {
        return new Human();
    }
}
