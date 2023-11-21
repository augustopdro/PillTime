package br.com.fiap.pilltime.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PillTime API")
                        .description(" Uma API para o sistema de lembretes de remedios")
                        .summary("A API do PillTime serve como a base para um aplicativo móvel que permite aos usuários criarem lembretes para nunca mais esquecerem o horário de tomar seus remédios.")
                        .version("V1")
                        .contact(new Contact()
                                .name("Pedro")
                                .email("rm94990@fiap.com.br")
                        )
                        .license(new License()
                                .name("MIT Open Soucer")
                                .url("http://pilltime.com/licenca")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }

}
