package org.sonthai.sleep_tracker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    type = SecuritySchemeType.HTTP,
    name = "basicAuth",
    scheme = "basic")
@SecurityScheme(
    type = SecuritySchemeType.HTTP,
    name = "bearerAuth",
    scheme = "bearer",
    bearerFormat = "Bearer",
    in = SecuritySchemeIn.HEADER
)
@SecurityScheme(
    name = "google_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            tokenUrl = "http://localhost:8080/login/oauth2/code/google",
            authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth",
            scopes = {
                @OAuthScope(name = "email profile", description = "Access to your email address and profile info")
            }
        ))
)
@SecurityScheme(
    name = "github_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            tokenUrl = "http://localhost:8080/login/oauth2/code/github",
            authorizationUrl = "https://github.com/login/oauth/authorize",
            scopes = {
                @OAuthScope(name = "user:email read:user", description = "Access to your email address and user info")
            }
        ))
)
@SecurityScheme(
    name = "facebook_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            tokenUrl = "http://localhost:8080/login/oauth2/code/facebook",
            authorizationUrl = "https://facebook.com/v17.0/dialog/oauth",
            scopes = {
                @OAuthScope(name = "email,public_profile", description = "Access to your email address and public profile info")
            }
        ))
)
@OpenAPIDefinition(
    info = @Info(
        title = "Sleep Tracker App",
        version = "0.0.1-SNAPSHOT",
        description = "Application which lets users control their sleeping habits"
            + "</br>User must login to use application."
            + "</br>For login method:"
            + "</br>Register and Login with Basic Auth."
            + "</br>Get Bearer Token for authentication: "
            + "[Google](https://accounts.google.com/o/oauth2/v2/auth?client_id=450982459846-die6mu5a8nn7d0go8c4invio7l3a5puu.apps.googleusercontent.com&redirect_uri=http://localhost:8080/login/oauth2/code/google&response_type=code&scope=profile%20email&access_type=offline)\t"
            + "[Github](https://github.com/login/oauth/authorize?client_id=953e0f21abfa0c2eee61&redirect_uri=http://localhost:8080/login/oauth2/code/github&scope=user:email%20read:user)\t"
            + "[Facebook](https://facebook.com/v17.0/dialog/oauth?client_id=395654748851809&redirect_uri=http://localhost:8080/login/oauth2/code/facebook&scope=email,public_profile)"
            + "</br>Or use swagger oauth2 authorize"
    ),
    security = {
        @SecurityRequirement(name = "basicAuth"),
        @SecurityRequirement(name = "bearerAuth"),
        @SecurityRequirement(name = "google_auth"),
        @SecurityRequirement(name = "github_auth"),
        @SecurityRequirement(name = "facebook_auth")
    }
)
public class OpenAPIConfig {}
