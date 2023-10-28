package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.mapper.worker.WorkerMapper;
import com.example.springbootdemo.service.AdminService;
import com.example.springbootdemo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    /*
    *   根据
    * */
    @GetMapping("/worker/information")
    public WorkerBean queryPersonalInformation(String worker_id){
        WorkerBean workerBean = workerService.queryPersonalInformation(worker_id);
        return workerBean;
    }





}
