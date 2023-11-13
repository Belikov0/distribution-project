package com.example.springbootdemo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.mapper.worker.WorkerMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

public class WorkerJwtInterceptor implements HandlerInterceptor {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        if (StringUtils.isBlank(token)){
            throw new ServiceException("401", "no token");
        }

        String worker_id;
        try {
            worker_id = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException("401", "no token 0");
        }

        WorkerBean workerBean = workerMapper.queryPersonalInformation(worker_id);
        if (workerBean == null){
            throw new ServiceException("401", "no such worker");
        }

        // 验证jwt密码
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(workerBean.getWorker_tel())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "token failure");
        }

        return true;
    }
}
