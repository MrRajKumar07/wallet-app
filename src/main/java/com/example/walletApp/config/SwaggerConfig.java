package com.example.walletApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wallet Application API")
                        .version("V1.0")
                        .description("Backend API for the Wallet App project....!")
                        .contact(new Contact()
                                .name("Raj Kumar")
                                .email("rajkumar951ce@gmail.com")));
    }
}