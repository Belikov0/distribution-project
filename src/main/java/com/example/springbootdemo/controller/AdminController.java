package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.AdminBean;
import com.example.springbootdemo.bean.ResultBean;
import com.example.springbootdemo.exception.AuthorityException;
import com.example.springbootdemo.exception.ResourceNotFoundException;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.service.AdminService;
import com.example.springbootdemo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/logIn")
    @ResponseBody
    public ResultBean logIn(String admin_id, String admin_password){
        AdminBean adminBean = adminService.queryAdmin(admin_id);
        if (adminBean == null){
            throw new ResourceNotFoundException("不存在用户");
        }
        if (!admin_password.equals(adminBean.getAdmin_password())){
            throw new AuthorityException("密码错误");
        }
        String token = TokenUtil.generateToken(adminBean.getAdmin_id(), adminBean.getAdmin_password());
        return new ResultBean(token, adminBean);
    }
}
