package com.example.springbootdemo.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkerBean {
    private String worker_id;
    private String worker_name;
    private String worker_gender;
    private int worker_age;
    private String worker_tel;
    private String depart_name;
    private String token;


}
