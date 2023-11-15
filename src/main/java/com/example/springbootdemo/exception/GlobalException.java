package com.example.springbootdemo.exception;
import com.example.springbootdemo.bean.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.transform.Result;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED) // code: 501
    @ResponseBody
    public String serviceException(ServiceException e){
        return e.getMessage();
    }

    @ExceptionHandler(AuthorityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)    // code: 401
    @ResponseBody
    public String authorityException(AuthorityException e){
        return e.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)   // code: 404
    @ResponseBody
    public String resourceNotFoundException(ResourceNotFoundException e){
        return e.getMessage();
    }


}
