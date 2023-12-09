package org.sonthai.sleep_tracker.security;

import org.sonthai.sleep_tracker.constant.SecurityConstant;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class BasicAuthSecurity {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.requestMatcher(
            request -> {
              String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
              return auth == null || !auth.startsWith(SecurityConstant.BEARER);
            })
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()

        .httpBasic()
        .and()
        .formLogin()
        .disable()
        .authorizeHttpRequests(registry -> registry.anyRequest().authenticated())
        .exceptionHandling(
            handler -> handler.authenticationEntryPoint(new UnauthorizedExceptionHandler()))
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
