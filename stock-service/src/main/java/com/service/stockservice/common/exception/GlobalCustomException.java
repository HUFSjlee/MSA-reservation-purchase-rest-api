package com.service.stockservice.common.exception;


import com.service.stockservice.common.response.ResponseCode;

public class GlobalCustomException extends BusinessException {
    public GlobalCustomException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public GlobalCustomException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public GlobalCustomException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
