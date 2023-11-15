package com.example.springbootdemo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springbootdemo.bean.AdminBean;
import com.example.springbootdemo.bean.WorkerBean;
import com.example.springbootdemo.exception.AuthorityException;
import com.example.springbootdemo.exception.ResourceNotFoundException;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.mapper.admin.AdminMapper;
import com.example.springbootdemo.service.AdminService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminJwtInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminService adminService;

    @Bean
    private AdminService getAdminService(){
        return adminService;
    }

    @Getter
    @Setter
    static class Info{
        String id;
        String token;

        public Info(String id, String token) {
            this.id = id;
            this.token = token;
        }
    }

    public static Info authorizeToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        if (StringUtils.isBlank(token)){
            throw new AuthorityException("No token");
        }

        String id;
        try {
            id = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e){
            throw new AuthorityException("No token");
        }

        return new Info(id, token);

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Info info = authorizeToken(request, response, handler);
        String admin_id = info.getId();
        String token = info.getToken();

        AdminBean adminBean =  adminService.queryAdmin(admin_id);
        if (adminBean == null){
            throw new AuthorityException("no such worker");
        }

        // 验证jwt密码
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(adminBean.getAdmin_password())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AuthorityException("token failure");
        }

        return true;
    }
}
