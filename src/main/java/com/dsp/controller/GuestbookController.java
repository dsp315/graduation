package com.dsp.controller;

import com.dsp.domain.Car;
import com.dsp.domain.Guestbook;
import com.dsp.domain.User;
import com.dsp.excetion.MyException;
import com.dsp.service.GuestbookService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

    @Autowired
    GuestbookService guestbookService;


    @GetMapping("")
    public String toListUI(){
        return "admin/guestbook/guestbookList";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultVO toGuestbookList(Guestbook param){
        ResultVO resultVO = new ResultVO();
        if(param.getStartDate() != null || param.getEndDate() != null) {
            /*模糊查询*/
            try {
                List<Guestbook> list = guestbookService.getGuestbookAndLike(param);
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            } catch (MyException e) {
                resultVO.setMsg(e.getMessage());
            }
        } else{
            /*查询所有*/
            try {
                List<Guestbook> list = guestbookService.getGuestbookList();
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
    public ResultVO delGuestbook(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =guestbookService.delGuestbookById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
    /*增加*/
    /*添加*/
    @PostMapping("")
    @ResponseBody
    public ResultVO<Object> addGuestbook(HttpSession session, Guestbook guestbook){
        ResultVO resultVO = new ResultVO();
        try {
            User user = (User) session.getAttribute("info");
            guestbook.setUserId(user.getUId());
            int i = guestbookService.addGuestbook(guestbook);
            resultVO.setCode(0);
            resultVO.setMsg("发布维修成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }

}
