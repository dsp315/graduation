package com.dsp.controller;

import com.dsp.domain.Admin;
import com.dsp.domain.Notice;
import com.dsp.excetion.MyException;
import com.dsp.service.AdminService;
import com.dsp.service.NoticeService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("")
    public String toIndexNotice(){
        return "admin/notice/noticeList";
    }
    /*查询所有*/
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getNoticeList(Notice param) {
        ResultVO resultVO = new ResultVO();
        if(param.getNTitle() != null || param.getStartDate() != null || param.getEndDate() != null) {
            /*模糊查询*/
            try {
                List<Notice> list = noticeService.getNoticeAndLike(param);
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            } catch (MyException e) {
                resultVO.setMsg(e.getMessage());
            }
        } else{
            /*查询所有*/
            try {
                List<Notice> list = noticeService.getNoticeList();
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            } catch (MyException e) {
                resultVO.setMsg(e.getMessage());
            }
        }
        return resultVO;
    }
    /*删除*/
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO<Object> delNotice(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =noticeService.delNoticeById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
    /**
     * 添加
     */
    /*跳转到添加页面*/
    @GetMapping("/add/ui")
    public String toNoticeAdd(){
        return "admin/notice/noticeAdd";
    }

    /*添加*/
    @PostMapping("")
    @ResponseBody
    public ResultVO<Object> addNotice(Notice notice, HttpSession session){
        ResultVO resultVO = new ResultVO();
        try {
            //获取管理员信息
            Admin admin = (Admin) session.getAttribute("info");
            //设置管理员主键
            notice.setAdminPuId(admin.getAId());
            //获取现在时间
            Date date = new Date();
            //设置发布时间
            notice.setNReleaseTime(date);
            int i = noticeService.addNotice(notice);
            resultVO.setCode(0);
            resultVO.setMsg("发布公告成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }
    /**
     * 修改
     */
    @Resource
    AdminService adminService;
    /*根据id查询并跳转到修改页面*/
    @GetMapping("/{id}")
    public String getNoticeById(@PathVariable Integer id, Model model){
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice",notice);
        //获取管理员信息
        Integer adminPuId = notice.getAdminPuId();
        Admin admin = adminService.getAdmin(adminPuId);
        model.addAttribute("admin",admin);
        return "admin/notice/noticeEdit";
    }
    /*修改*/
    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updateNotice(Notice notice,HttpSession session){
        ResultVO resultVO =new ResultVO();
        try {
            //获取管理员信息
            Admin admin = (Admin) session.getAttribute("info");
            //设置管理员主键
            notice.setAdminMoId(admin.getAId());
            //获取现在时间
            Date date = new Date();
            //设置修改时间
            notice.setNModifeTime(date);
            int i = noticeService.updateNotice(notice);
            resultVO.setCode(0);
            resultVO.setMsg("修改成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

}
