package com.dsp.common.config;

import com.dsp.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginInterceptor")
    private LoginInterceptor interceptor;

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =registry.addInterceptor(interceptor);

        //拦截的请求
        registration.addPathPatterns("/**");

        //通过的请求
        registration.excludePathPatterns(
                "/login",
                "/captcha",        //验证码
                "/admin/login",    //管理员登录
                "/admin//logout",  //管理员退出
                "/user/login",     //管理员登录
                "/user//logout",   //用户退出
                "/css/**",         /*静态文件*/
                "/api/**",
                "/js/**",
                "/images/**",
                "/lib/**"
        );
    }
}
