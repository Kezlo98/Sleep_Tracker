package org.sonthai.sleep_tracker.security.filter;

import org.sonthai.sleep_tracker.constant.SecurityConstant;
import org.sonthai.sleep_tracker.security.TokenManager;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Component
@NoArgsConstructor
public class OAuth2AuthorizationFilter extends GenericFilterBean {

    private TokenManager tokenManager;

    @Autowired
    public OAuth2AuthorizationFilter(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = extractToken((HttpServletRequest)request);
        if(StringUtils.hasText(token)){
            Authentication authentication = tokenManager.get(token);
            if(authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);
    }

    private String extractToken(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(SecurityConstant.BEARER)){
            return token.substring(SecurityConstant.BEARER.length() + 1);
        }

        return token;
    }
}
