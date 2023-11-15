package com.example.springbootdemo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorityException extends RuntimeException{

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException() {

    }


}
