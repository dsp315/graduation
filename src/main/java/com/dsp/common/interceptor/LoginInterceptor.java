package com.dsp.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检测session是否有信息
        Object adminInfo = request.getSession().getAttribute("info");
        if (adminInfo ==null){
            //未登录
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
