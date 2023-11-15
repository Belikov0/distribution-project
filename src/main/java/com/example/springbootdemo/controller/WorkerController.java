package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.ResultBean;
import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.exception.ResourceNotFoundException;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.service.WorkerService;
import com.example.springbootdemo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/worker/logIn")
    @ResponseBody
    public ResultBean logIn(String worker_id){

        WorkerBean workerBean = workerService.queryPersonalInformation(worker_id);
        if (workerBean == null){
            throw new ResourceNotFoundException("Worker not found");
        }
        String token = TokenUtil.generateToken(workerBean.getWorker_id(), workerBean.getWorker_tel());

        return new ResultBean(token, workerBean);
    }


    @GetMapping("/worker/test")
    public void test(){
        /* 测试 */
    }

    @GetMapping("/worker/information")
    @ResponseBody
    public ResultBean queryPersonalInformation(String worker_id){
        WorkerBean workerBean = workerService.queryPersonalInformation(worker_id);
        System.out.println(workerBean);

        return new ResultBean(workerBean);
    }

    @GetMapping("/worker/historyAttendance")
    public void getHistoryAttedance(String worker_id, String year, String month, String day){
        System.out.println(worker_id + " " + year + " " + month + " " + day);

    }

    @GetMapping("/worker/monthlyAttendance")
    public void queryMonthlyAttendance(String worker_id, String year, String month){
        System.out.println(worker_id + " " + year + " " + month );

    }

    @PostMapping("/worker/signIn")
    public boolean workerSignIn(@RequestParam String worker_id, @RequestBody Object obj){
        System.out.println(worker_id);
        System.out.println(obj);
        workerService.signIn(worker_id);

        return true;
    }

    @PostMapping("/worker/signOut")
    public boolean workerSignOut(String worker_id, @RequestBody Object obj){
        System.out.println(worker_id);
        System.out.println(obj);

        return true;
    }
}
