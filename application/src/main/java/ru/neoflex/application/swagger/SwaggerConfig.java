package ru.neoflex.application.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Application")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("adiatlov@neoflex.ru")
                                                .name("Diatlov, Anton")
                                )
                );
    }
}
