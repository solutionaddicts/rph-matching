package com.addicts.rph.matching.config;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@Configuration
public class OAuthSwaggerSecurityConfig {

  private static final String SECURITY_SCHEME_NAME = "spring_oauth";
  private static final String CLIENT_ID = "rph-auth-CLIENT-ID";
  private static final String CLIENT_SECRET = "U1ZCLn5dOEFBQyFaYHUjTDNiPUU4OS9bLFEmXUZ0LT4sKDQzbV9mXC55NWokWXdzdw==";
  @Value("${auth-host}")
  private String host;

  @Bean
  public SecurityConfiguration securityInfo() {
    return SecurityConfigurationBuilder.builder()
        .clientId(CLIENT_ID)
        .clientSecret(CLIENT_SECRET)
        .scopeSeparator(" ")
        .build();
  }

  private AuthorizationScope[] scopes() {
    AuthorizationScope[] scopes = {
        new AuthorizationScope("read", "for read operations"),
        new AuthorizationScope("write", "for write operations")
    };

    return scopes;
  }

  private List<SecurityReference> securityReferences() {
    return Lists.newArrayList(new SecurityReference(SECURITY_SCHEME_NAME, scopes()));
  }

  @Bean
  public SecurityScheme securityScheme() {
    LoginEndpoint loginEndpoint = new LoginEndpoint(host + "/rph/auth/oauth/authorize");
    GrantType grantType = new ImplicitGrant(loginEndpoint, OAuth2AccessToken.ACCESS_TOKEN);
    return new OAuthBuilder().name(SECURITY_SCHEME_NAME)
        .grantTypes(Lists.newArrayList(grantType))
        .scopes(Lists.newArrayList(scopes()))
        .build();
  }

  @Bean
  public SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(securityReferences())
        .forPaths(s -> true)
        .build();
  }
}