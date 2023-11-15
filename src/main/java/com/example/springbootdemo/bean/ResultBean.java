package com.example.springbootdemo.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class ResultBean {

    private String token;
    private Object obj;

    public ResultBean(Object obj) {
        this.obj = obj;
    }

    public ResultBean(String token, Object obj) {
        this.token = token;
        this.obj = obj;
    }
}
