package com.addicts.rph.matching.config;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sravantatikonda
 */
@Configuration
@EnableSwagger2
@Slf4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig implements WebMvcConfigurer {

  private static final String BASE_PACKAGE = "com.addicts.rph.matching.controller";

  @Autowired
  private SecurityScheme securityScheme;

  @Autowired
  private SecurityContext securityContext;

  @Bean
  public Docket api() {
    log.debug("Starting Swagger");
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(metaData())
        .select()
        .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
        .paths(PathSelectors.regex("/.*"))
        .build()
        .securityContexts(Collections.singletonList(securityContext))
        .securitySchemes(Collections.singletonList(securityScheme));
  }

//  private SecurityContext securityContext() {
//    return SecurityContext.builder()
//        .securityReferences(
//            Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
//        .forPaths(PathSelectors.regex("/foos.*"))
//        .build();
//  }
//
//  private SecurityScheme securityScheme() {
//    GrantType grantType = new AuthorizationCodeGrantBuilder()
//        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/oauth/token", "oauthtoken"))
//        .tokenRequestEndpoint(
//            new TokenRequestEndpoint(AUTH_SERVER + "/authorize", clientId, clientSecret))
//        .build();
//
//    return new OAuthBuilder().name("spring_oauth")
//        .grantTypes(Collections.singletonList(grantType))
//        .scopes(Arrays.asList(scopes()))
//        .build();
//  }
//
//  private AuthorizationScope[] scopes() {
//    return new AuthorizationScope[]{
//        new AuthorizationScope("read", "for read operations"),
//        new AuthorizationScope("write", "for write operations")};
//  }
//
//  private SecurityReference basicAuthReference() {
//    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
//  }

  private ApiInfo metaData() {
    Contact contact = new Contact(
        "Sravan Tatikonda",
        "https://rph.com",
        "sravan.tatikonda@solutionaddicts.com");
    return new ApiInfoBuilder().title("RPH Matching Application")
        .description("\" Rest APIs for RPH Matching App \"")
        .version("1.0")
        .contact(contact).build();
  }
}
