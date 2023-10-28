package com.example.springbootdemo.serviceIpml;

import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.mapper.worker.WorkerMapper;
import com.example.springbootdemo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImplementation implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public void signIn(String worker_id) {

    }

    @Override
    public void signOut(String worker_id) {

    }

    @Override
    public WorkerBean queryPersonalInformation(String worker_id) {
        WorkerBean workerBean = workerMapper.queryPersonalInformation(worker_id);

        return workerBean;
    }


}
