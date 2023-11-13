package com.example.springbootdemo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springbootdemo.bean.AttendanceBean;
import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.service.WorkerService;
import com.example.springbootdemo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class WorkerController {
    @Autowired
    private WorkerService workerService;


    @GetMapping("/worker/login")
    @ResponseBody
    public WorkerBean login(String worker_id){
        WorkerBean workerBean = workerService.queryPersonalInformation(worker_id);
        if (workerBean == null){
            throw new ServiceException("");
        }
        String token = TokenUtil.generateToken(workerBean.getWorker_id(), workerBean.getWorker_tel());
        workerBean.setToken(token);
        return workerBean;
    }

    @GetMapping("/worker/test")
    public void test(){

    }

    @GetMapping("/worker/information")
    public WorkerBean queryPersonalInformation(String worker_id){
        WorkerBean workerBean = workerService.queryPersonalInformation(worker_id);
        return workerBean;
    }

    @GetMapping("/worker/historyAttendance")
    public void getHistoryAttedance(String worker_id, String year, String month, String day){
        System.out.println(worker_id + year + month + day);
    }

    @GetMapping("/worker/monthlyAttendance")
    public void queryMonthlyAttendance(String worker_id, String year, String month){
//        System.out.println(worker_id + year + month);
    }


    /**
    * @description:
    * @param:
    * @return:
    * @Author: Smile
    * @Date: 2023/10/29
    */
    @GetMapping("/worker/signIn")
    public AttendanceBean workerSignIn(String worker_id){
        /*
            1. 判断是否出差、旷工、请假
            2. 否，则判断是否在规定时间内
         */


        return new AttendanceBean();
    }





}
