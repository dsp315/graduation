package com.dsp.common.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    /*登录跳转*/
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /*管理跳转*/
    @RequestMapping("/welcome")
    public String toWelcome(){
        return "admin/home/welcome";
    }


    /*图形校验码*/
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
