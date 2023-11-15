package com.example.springbootdemo.mapper.admin;

import com.example.springbootdemo.bean.AdminBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AdminMapper {

    List<AdminBean> testify();

    AdminBean selectAdminById(String admin_id);

}
