package org.sonthai.sleep_tracker.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2")
@Data
public class OAuth2Properties {

    private Map<String, ClientProperties> client;

    public String getRedirectUriByRegistrationId(String registrationId){
        return client.get(registrationId).getRedirectUri();
    }

    public String getScopeByRegistrationId(String registrationId){
        return client.get(registrationId).getScope();
    }

}
