package org.sonthai.sleep_tracker.constant;

public enum RegistrationEnum {
    BASIC, GOOGLE, GITHUB, FACEBOOK;

    public static RegistrationEnum value(String registrationId){
        try{
            return RegistrationEnum.valueOf(registrationId.toUpperCase());
        } catch (Exception e){
            return RegistrationEnum.GOOGLE;
        }
    }
}
