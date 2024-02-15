package com.service.newsfeedservice.common.exception;

import com.service.newsfeedservice.common.response.ResponseCode;

public class NotFoundPostException extends BusinessException {
    public NotFoundPostException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public NotFoundPostException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public NotFoundPostException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
