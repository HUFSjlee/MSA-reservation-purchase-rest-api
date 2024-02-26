package com.service.productservice.common.exception;

import com.service.productservice.common.response.ResponseCode;

public class UsernameNotFoundException extends BusinessException {
    public UsernameNotFoundException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public UsernameNotFoundException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public UsernameNotFoundException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
