package org.sonthai.sleep_tracker.security;

import org.sonthai.sleep_tracker.constant.SecurityConstant;
import org.sonthai.sleep_tracker.security.filter.OAuth2AuthenticationFilter;
import org.sonthai.sleep_tracker.security.filter.OAuth2AuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Order(2)
public class OAuth2SecurityConfiguration {

    private final OAuth2AuthenticationFilter auth2AuthenticationFilter;
    private final OAuth2AuthorizationFilter auth2AuthorizationFilter;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .requestMatcher(request -> {
                    String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
                    return auth != null && auth.startsWith(SecurityConstant.BEARER);
                })
                .csrf().disable()

                .addFilterBefore(auth2AuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(auth2AuthorizationFilter, BasicAuthenticationFilter.class)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests(registry -> registry.anyRequest().authenticated())

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new OAuth2AuthenticationEntryPoint());
                    httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new OAuth2AccessDeniedHandler());
                })
                .build();

    }
}
