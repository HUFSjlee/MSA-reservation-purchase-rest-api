package com.service.orderservice.common.exception;

import com.service.orderservice.common.response.ResponseCode;

public class OutOfStockException extends BusinessException {
    public OutOfStockException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public OutOfStockException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public OutOfStockException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
