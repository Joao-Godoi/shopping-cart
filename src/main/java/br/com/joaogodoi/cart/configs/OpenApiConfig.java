package br.com.joaogodoi.cart.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAp() {
        return new OpenAPI().info(new Info()
                .title("Shopping cart API")
                .version("v1.0")
                .description("API created using Java 17 and Spring Boot 3 for manage a shopping cart and products"));
    }
}
