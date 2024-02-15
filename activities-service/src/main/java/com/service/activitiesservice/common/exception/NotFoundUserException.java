package com.service.activitiesservice.common.exception;


import com.service.activitiesservice.common.response.ResponseCode;

public class NotFoundUserException extends BusinessException {

    public NotFoundUserException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public NotFoundUserException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public NotFoundUserException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
