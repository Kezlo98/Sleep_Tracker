package org.sonthai.sleep_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  private final String[] whitelist = {
      "/api/user/register",
      "/login/**",
      "/swagger-ui/**",
      "/swagger-resources/**",
      "/v3/api-docs/**",
      "webjars/**",
      "/swagger-ui.html",
  };

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web -> web.ignoring().antMatchers(whitelist));
  }
}
