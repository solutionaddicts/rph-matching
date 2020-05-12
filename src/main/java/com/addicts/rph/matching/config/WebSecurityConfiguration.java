package com.addicts.rph.matching.config;

import com.addicts.rph.matching.security.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sravantatikonda
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${security.signing-key}")
  private String SIGNING_KEY;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .headers().frameOptions().disable()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .authorizeRequests()
//      .antMatchers("/professional/sec/**").hasAuthority(UserType.Professional.getValue())
//         .antMatchers("/internal/**").hasAuthority(UserType.Admin.getValue())
        //       .antMatchers("/internal/**").hasAuthority(UserType.CSR.getValue())
        //  .antMatchers("/sec/**").authenticated()
        .anyRequest().permitAll()
        .antMatchers("/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**").permitAll()
        .and()
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), SIGNING_KEY));
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }
}



