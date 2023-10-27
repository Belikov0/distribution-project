package com.example.springbootdemo.mapper.worker;

import com.example.springbootdemo.bean.WorkerBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface WorkerMapper {
    List<WorkerBean> findAll();
}
