package com.example.springbootdemo.serviceIpml;

import com.example.springbootdemo.bean.AttendanceBean;
import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.mapper.worker.WorkerMapper;
import com.example.springbootdemo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.sql.Date;
import java.sql.Time;

@Service
public class WorkerServiceImplementation implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public void signIn(String worker_id) throws ServerException {




        AttendanceBean attendanceBean = workerMapper.selectLastAttendanceByWorkerId(worker_id);
        String lastType = attendanceBean.getAttendance_type();

        // 请假出差无法签到
        if (lastType.equals("请假") || lastType.equals("出差")){
            throw new ServerException("您正处于" + lastType + "中，无法签到");
        }
        // 旷工无法签到
        if (lastType.equals("旷工") ){
            throw new ServerException("您已被记录为旷工，无法签到");
        }
        //最后一条是签退时，无法签到
        if (lastType.equals("签退") ){
            throw new ServerException("尚未签退，无法签到");
        }
        /**
         * 如果最后一条是签退时，可以签到
         */

        String lastId = workerMapper.selectLastAttendanceId();
        String attendance_id = Integer.toString((Integer.parseInt(lastId) + 1));

        /**
         *  判断是否迟到或者早退
         */

        System.out.println(attendanceBean + " " + lastId);

        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        String type;
        String status;
        type = "签到";
        status = "正常";

//        workerMapper.insertIntoAttendance(attendance_id, worker_id, date.toString(), time.toString(), type, status);
    }

    @Override
    public void signOut(String worker_id) {

    }

    @Override
    public WorkerBean queryPersonalInformation(String worker_id) {
        return workerMapper.selectWorkerById(worker_id);
    }




}
