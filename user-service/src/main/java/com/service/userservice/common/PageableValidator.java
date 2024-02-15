package com.service.userservice.common;

import com.service.userservice.common.exception.BadParametersException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableValidator {
    public void validate(Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        if(page<0 || size>50) {
            throw new BadParametersException();
        }
    }
}