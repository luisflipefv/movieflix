package dev.luisf.movieflix.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi(){

        Contact contact = new Contact();
        contact.name("Luis Felipe");
        contact.email("luisfelipefv8@hotmail.com");

        Info info = new Info();
        info.title("Movieflix API");
        info.version("1.0");
        info.description("API para gerenciamento de catalogos de filmes e usuários.");
        info.contact(contact);

        return new OpenAPI().info(info);
    }

}
