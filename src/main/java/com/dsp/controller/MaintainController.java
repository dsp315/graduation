package com.dsp.controller;

import com.dsp.domain.Maintain;
import com.dsp.domain.User;
import com.dsp.excetion.MyException;
import com.dsp.service.MaintainService;
import com.dsp.service.UsersService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/maintain")
public class MaintainController {

    @Autowired
    MaintainService maintainService;

    @GetMapping("")
    public String toListUI(){
        return "admin/maintain/maintainList";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultVO toMaintainList(Maintain param){
        ResultVO resultVO = new ResultVO();
        if(param.getStartDate() != null || param.getEndDate() != null) {
            /*模糊查询*/
            try {
                List<Maintain> list = maintainService.getMaintainAndLike(param);
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            } catch (MyException e) {
                resultVO.setMsg(e.getMessage());
            }
        } else{
            /*查询所有*/
            try {
                List<Maintain> list = maintainService.getNoticeList();
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
    public ResultVO delMaintain(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =maintainService.delMaintainById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    @Autowired
    UsersService userService;
    /**
     * 修改
     */
    /*根据id查询并跳转到修改页面*/
    @GetMapping("/{id}")
    public String getMaintainById(@PathVariable Integer id, Model model){
        Maintain maintain = maintainService.getMaintainById(id);
        model.addAttribute("maintain",maintain);
        User user = userService.selectUserById(maintain.getUserId());
        model.addAttribute("user",user);
        return "admin/maintain/maintainEdit";
    }
}
