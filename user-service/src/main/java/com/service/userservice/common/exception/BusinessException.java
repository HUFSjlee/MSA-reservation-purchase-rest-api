package com.service.userservice.common.exception;

import com.service.userservice.common.response.ResponseCode;

public class BusinessException extends RuntimeException {
    protected ResponseCode responseCode;
    protected String customMessage;

    public BusinessException(String customMessage) {
        super(customMessage);
        this.customMessage = customMessage;
    }

    public BusinessException(ResponseCode responseCode, String customMessage) {
        super(customMessage);
        this.responseCode = responseCode;
        this.customMessage = customMessage;
    }
}
