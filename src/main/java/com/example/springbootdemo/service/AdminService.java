package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.AdminBean;
import org.springframework.context.annotation.Bean;


public interface AdminService {
    AdminBean queryAdmin(String admin_id);

}
