package com.example.springbootdemo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springbootdemo.bean.AdminBean;
import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.exception.AuthorityException;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.mapper.worker.WorkerMapper;
import com.example.springbootdemo.service.AdminService;
import com.example.springbootdemo.service.WorkerService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

public class WorkerJwtInterceptor implements HandlerInterceptor {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private AdminService adminService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminJwtInterceptor.Info info = AdminJwtInterceptor.authorizeToken(request, response, handler);
        String id = info.getId();
        String token = info.getToken();

        AdminBean adminBean = adminService.queryAdmin(id);
        WorkerBean workerBean = workerService.queryPersonalInformation(id);
        if (workerBean == null && adminBean == null){
            throw new AuthorityException("no such user");
        }

        // 验证jwt密码
        JWTVerifier jwtVerifier;
        if (adminBean == null){
            jwtVerifier = JWT.require(Algorithm.HMAC256(workerBean.getWorker_tel())).build();
        }else{
            jwtVerifier = JWT.require(Algorithm.HMAC256(adminBean.getAdmin_password())).build();
        }

        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AuthorityException("Token failure");
        }

        return true;
    }
}
