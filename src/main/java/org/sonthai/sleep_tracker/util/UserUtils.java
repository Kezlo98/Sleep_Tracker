package org.sonthai.sleep_tracker.util;

import org.sonthai.sleep_tracker.constant.RegistrationEnum;
import org.sonthai.sleep_tracker.model.dto.UserDto;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class UserUtils {

    public static UserDto getUserDtoFromAuthentication(Authentication authentication){
        if(authentication == null){
            return null;
        }
        UserDto userDto = new UserDto();
        if(authentication instanceof OAuth2AuthenticationToken){
            userDto.setUsername(getUserName(((OAuth2AuthenticationToken) authentication).getPrincipal().getAttributes(), ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId()));
            userDto.setRegistrationId(RegistrationEnum.valueOf(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().toUpperCase()));
        } else {
            userDto.setUsername(authentication.getName());
            userDto.setRegistrationId(RegistrationEnum.BASIC);
        }

        return userDto;
    }

    public static String getUserName(Map<String, Object> attributes, String registrationId) {
        if (RegistrationEnum.value(registrationId) == RegistrationEnum.GITHUB) {
            return  (String) attributes.get("login");
        }
        return (String) attributes.get("email");
    }
}
