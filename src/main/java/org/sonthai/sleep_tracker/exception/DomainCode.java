package org.sonthai.sleep_tracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainCode {

    SUCCESS("0000", "Success"),
    INVALID_REQUEST("0001","Invalid Request: %s"),
    USERNAME_IS_EXISTED("0003", "Username is existed: %s"),
    USERNAME_IS_NOT_EXISTED("0004", "Username is not existed: %s, registrationId: %s"),
    USER_IS_NOT_EXISTED("0005", "User is not existed: %s"),
    SLEEP_IS_NOT_EXISTED("0006", "Sleep is not existed: %s"),
    GENERAL_ERROR("9999", "Something's wrong. Please, try again."),
    ;

    private String code;

    private String value;

    public String valueAsString(Object[] args) {
        return String.format(String.format("%s", this.value), args);
    }
}
