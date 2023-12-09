package org.sonthai.sleep_tracker.util;


import org.sonthai.sleep_tracker.exception.DomainCode;
import org.sonthai.sleep_tracker.model.GeneralResponse;
import org.sonthai.sleep_tracker.model.ResponseStatus;

public class ResponseUtils {

    public static GeneralResponse<?> buildSuccessResponse(Object data){
        return new GeneralResponse<>()
                .setStatus(
                        new ResponseStatus(
                                DomainCode.SUCCESS.getCode(), DomainCode.SUCCESS.getValue()))
                .setData(data);
    }
}
