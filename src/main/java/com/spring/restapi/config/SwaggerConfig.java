package com.spring.restapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
               .info(new io.swagger.v3.oas.models.info.Info()
                       .title("Spring REST API")
                       .version("1.0.0")
                       .description("Rest Api - Backend"));
    }
}
