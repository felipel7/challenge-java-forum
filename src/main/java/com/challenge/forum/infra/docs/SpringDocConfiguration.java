package com.challenge.forum.infra.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.challenge.forum.api.utils.Constants.*;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .components(new Components().addSecuritySchemes(DOCS_BEARER_KEY,
                                                            new SecurityScheme()
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")
            ))
            .info(new Info()
                      .title(DOCS_APP_TITLE)
                      .description(DOCS_APP_DESCRIPTION)
                      .contact(new Contact().name(DOCS_APP_AUTHOR).url(DOCS_APP_AUTHOR_GITHUB)));
    }
}
