package com.service.productservice.common.exception;

import com.service.productservice.common.response.ResponseCode;

public class NotFoundCommentException extends BusinessException {
    public NotFoundCommentException() {
        super(ResponseCode.NOT_FOUND_RESOURCE, ResponseCode.NOT_FOUND_RESOURCE.getDefaultMessage());
    }

    public NotFoundCommentException(String customMessage) {
        super(ResponseCode.NOT_FOUND_RESOURCE, customMessage);
    }

    public NotFoundCommentException(ResponseCode responseCode, String customMessage) {
        super(responseCode, customMessage);
    }
}
