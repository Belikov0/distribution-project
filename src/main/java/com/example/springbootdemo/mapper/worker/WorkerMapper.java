package com.example.springbootdemo.mapper.worker;

import com.example.springbootdemo.bean.AttendanceBean;
import com.example.springbootdemo.bean.WorkerBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *  查询：
 *      查询单条：select + 单数 + By + 条件
 *      查询多条：select + 复数 + By + 条件
 *      查询全部：selectAllFrom + 表名
 *  插入：
 *
 */
@Mapper
@Component
public interface WorkerMapper {

    WorkerBean selectWorkerById(String worker_id);

    List<WorkerBean> selectAllFromWorkers();

    List<AttendanceBean> selectAllFromAttendance();

    List<AttendanceBean> selectDailyAttendanceById(String worker_id, String date);

    String selectLastAttendanceId();

    AttendanceBean selectLastAttendanceByWorkerId(String worker_id);

    void insertIntoAttendance(String attendance_id,
                              String worker_id,String date, String time, String type, String status);





}
