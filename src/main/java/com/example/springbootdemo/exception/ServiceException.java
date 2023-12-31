package com.example.springbootdemo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class ServiceException extends  RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
