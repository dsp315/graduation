package com.dsp.controller;

import com.dsp.domain.Admin;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.service.AdminService;
import com.dsp.vo.ResultVO;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ResponseBody
    public ResultVO login(Admin param, HttpSession session
             /* ,@RequestParam("captcha")String captcha,HttpServletRequest request */ ){

        ResultVO resultVO = new ResultVO();
        //验证码登录
        /* if (!CaptchaUtil.ver(captcha, request)) {
            resultVO.setMsg(MyEnum.ADMIN_LOGIN_CODE_FAIL.getMessage());
            return resultVO;
        } */
        try {
            Admin admin = adminService.login(param);
            resultVO.setCode(0);
            session.setAttribute("info",admin);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;


    }
    @GetMapping("/index")
    public String toIndex(Admin admin){
        return "admin/index";
    }

    @GetMapping("/info")
    public String toMyInfo(Model model){
        return "admin/adminInfo/info";
    }

    /**修改个人信息*/
    @PostMapping("/edit")
    @ResponseBody
    public ResultVO modifyAdminInfo(Admin admin,HttpServletRequest request){
        ResultVO resultVO = new ResultVO();

        try {
            int i = adminService.modifyAdminInfo(admin);
            if (i > 0){
                resultVO.setCode(0);
                resultVO.setMsg("保存成功");
                //更新session的值
                //获取session的值，  false为如果没有session的返回为false
                if (request.getSession(false) != null){
                    HttpSession session = request.getSession(false);
                    Admin beforeAdmin = (Admin) session.getAttribute("info");
                    //获取照片信息
                    admin.setAImg(beforeAdmin.getAImg());
                    admin.setAPassword(beforeAdmin.getAPassword());
                    session.setAttribute("info",admin);
                }
            }
        }catch (Exception e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
    /*修改照片*/

    /**
     * MultipartFile:表单提交获取文件
     */
    @PostMapping("/editImg")
    @ResponseBody
    public ResultVO editImg(MultipartFile fileUpload, String paramName, HttpServletRequest request){
        ResultVO resultVO = new ResultVO();
        try {
            Map<String,String> map = adminService.editImg(fileUpload,paramName, request);
            resultVO.setCode(0);
            resultVO.setMsg("修改头像成功");
            //更新session的值
            //获取session的值，  false为如果没有session的返回为false
            if (request.getSession(false) != null){
                HttpSession session = request.getSession(false);
                Admin beforeAdmin = (Admin) session.getAttribute("info");
                //获取其他信息
                Admin admin = new Admin();
                admin.setAId(beforeAdmin.getAId());
                admin.setAName(beforeAdmin.getAName());
                admin.setAPassword(beforeAdmin.getAPassword());
                admin.setAPhone(beforeAdmin.getAPhone());
                admin.setAImg(map.get("filePath"));
                session.setAttribute("info",admin);
            }
        }catch (Exception e ){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
}
