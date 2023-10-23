package br.com.meow.meow.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gatos")
                        .version("v1")
                        .description("Gatinho macio \n" +
                                "Gatinho quentinho \n" +
                                "Bolinha de pelo \n" +
                                "\n" +
                                "Gatinho feliz \n" +
                                "Gatinho dorminhoco \n" +
                                "Miau, miau, miau \n" +
                                "\n")
                        .termsOfService("https://http.cat/status/203")
                        .license(new License()
                                .name("204")
                                .url("https://http.cat/status/204")))
                        .externalDocs(new ExternalDocumentation()
                                .description("Encontre mais gatinhos aqui")
                                .url("https://http.cat/status/429"))
                        .servers(List.of(new Server().url("http://localhost:8080/").description("Meow meow")))
                        .tags(List.of(new Tag().name("Gatinhos").description("Tudo sobre gatinhos")))
                        .components(new Components()
                                .addSecuritySchemes("meow-meow", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("meow")
                                        .bearerFormat("meow")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("meow meow")))
                        .addSecurityItem(new SecurityRequirement().addList("meow-meow", Arrays.asList("meow", "meow")));
    }
}
