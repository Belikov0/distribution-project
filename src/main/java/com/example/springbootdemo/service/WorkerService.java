package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.WorkerBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;


public interface WorkerService {

    // 根据员工号进行签到
    public void signIn(String worker_id);

    // 根据员工号进行签退
    public void signOut(String worker_id);

    // 根据员工号查询信息
    public WorkerBean queryPersonalInformation(String worker_id);

//    // 查询每月总表
//    public void queryPersonalMonthlyAttendance(String worker_id, Date date);
//
//    // 查询每日考勤
//    public void queryPersonalDailyAttendance(String worker_id, Date date);



}
