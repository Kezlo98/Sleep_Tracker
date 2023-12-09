package org.sonthai.sleep_tracker.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String commence(DomainException e) {
        log.error("method: handleDomainException, ex: {}", e.getMessage());

        return e.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationServiceException(AuthenticationException e) {
        log.error("method: handleAuthenticationServiceException, ex: {}", e.getMessage());

        return e.getMessage();
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException e) {
        log.error("method: handleAccessDeniedException, ex: {}", e.getMessage());

        return "Access is denied. Please contact to your admin to get permission";
    }

}
