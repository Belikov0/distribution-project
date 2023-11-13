package com.example.springbootdemo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;


@Getter
@Setter
@ToString
public class AttendanceBean {
    private String worker_id;
    private String worker_name;
    private Date attendance_date;
    private Time attendance_time;
    private String attendance_type;
    private String attendance_status;


}
