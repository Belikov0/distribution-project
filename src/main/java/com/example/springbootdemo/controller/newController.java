package com.example.springbootdemo.controller;


import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.mapper.worker.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class newController {

    @Autowired
    private WorkerMapper workerMapper;


    @GetMapping("/worker")
    public String query(){
        List<WorkerBean> workerBeans = workerMapper.findAll();
        System.out.println(workerBeans);
        return "查询用户";
    }

}
