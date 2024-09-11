package com.app.course_crypto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAdditionalConfiguration {
    private final String TITLE = "COURSE WORK";
    private final String DESCRIPTION = "CryptoAPP";
    private final String VERSION = "1.0";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                );
    }
}