package com.service.orderservice.common.exception;


import com.service.orderservice.common.response.ResponseCode;

public class NotFoundResourceException extends BusinessException {

    public NotFoundResourceException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public NotFoundResourceException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public NotFoundResourceException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
