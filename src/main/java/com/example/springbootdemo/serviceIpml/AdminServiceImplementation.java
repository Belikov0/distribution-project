package com.example.springbootdemo.serviceIpml;

import com.example.springbootdemo.bean.AdminBean;
import com.example.springbootdemo.mapper.admin.AdminMapper;
import com.example.springbootdemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminBean queryAdmin(String admin_id){
        return adminMapper.selectAdminById(admin_id);
    }

}
