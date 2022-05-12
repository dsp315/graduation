package com.dsp.controller;

import com.dsp.domain.Building;
import com.dsp.domain.User;
import com.dsp.excetion.MyException;
import com.dsp.service.UsersService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @RequestMapping("")
    public String toUI(){
        return "admin/users/usersList";
    }
    /*查询*/
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getUserList(User param){
        ResultVO resultVO = new ResultVO();
        if (param.getUName() == null || param.getUPhone() == null){
            /*查询所有*/
            try {
                List<User> list = usersService.getUserList();
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            }catch (MyException e){
                resultVO.setMsg(e.getMessage());
            }
        }else {
            /*模糊查询*/
            try {
                List<Building> list = usersService.getUserAndLike(param);
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            }catch (MyException e){
                resultVO.setMsg(e.getMessage());
            }
        }
        return resultVO;
    }

}
