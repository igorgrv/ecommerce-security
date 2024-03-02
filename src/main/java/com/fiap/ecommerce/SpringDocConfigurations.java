package com.fiap.ecommerce;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@OpenAPIDefinition(info = @Info(title = "Ecommerce Monitor", version = "0.0.1", description = "The best place to watch movies", license = @License(name = "MIT License", url = "https://github.com/igorgrv/ecommerce-graduate")), 
security = @SecurityRequirement(name = "token"),
servers = {
    @Server(url = "http://localhost:8080"),
    @Server(url = "https://ecommerce-e2kw5mlx.b4a.run"),
})
public class SpringDocConfigurations {
}
