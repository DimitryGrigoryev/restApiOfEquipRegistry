package org.example.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class OpenApiConfiguration {
  private final PropertyResolverUtils propertyResolverUtils;

  public OpenApiConfiguration(PropertyResolverUtils propertyResolverUtils) {
    this.propertyResolverUtils = propertyResolverUtils;
  }

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info().title(message("api.title"))
            .description(message("api.description"))
            .version("v1.0.0")
            .license(new License().name(message("api.license")).url("https://Dimbas.ru")));
  }

  private String message(String property) {
    return this.propertyResolverUtils.resolve(property, Locale.getDefault());
  }
}
