package com.lalalazero.todos.aspect;

import com.lalalazero.todos.utils.JWT;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date 2019/1/10 下午11:16
 */
@Aspect
@Component
public class ConAspect extends HandlerInterceptorAdapter {

    @Before("execution(public * com.lalalazero.todos.controller.*.*(..))")
    public void before(){
        ServletRequestAttributes sra =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String methodName = request.getRequestURI();
        // 登陆和注册不做拦截
        if(methodName.contains("/api/login") || methodName.contains("/api/register")){
            return;
        }

        String jwt = request.getHeader("token");
        HttpServletResponse response = sra.getResponse();
        if(StringUtils.isEmpty(jwt)){
            try {
                response.sendError(403);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        // 验证 jwt token
        if(!JWT.getInstance().isTokenValid(jwt)){
            try {
                response.sendError(403);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        };
        return;

    }
}
