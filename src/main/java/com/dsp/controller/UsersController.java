package com.dsp.controller;

import com.dsp.domain.User;
import com.dsp.excetion.MyException;
import com.dsp.service.UsersService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (param.getUName() == null && param.getUPhone() == null){
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
                List<User> list = usersService.getUserAndLike(param);
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            }catch (MyException e){
                resultVO.setMsg(e.getMessage());
            }
        }
        return resultVO;
    }
    /*添加*/
    @GetMapping("/add/ui")
    public String toAddUI(){
        return "admin/users/usersAdd";
    }
    @PostMapping("")
    @ResponseBody
    public ResultVO addUsers(User user){
        ResultVO resultVO = new ResultVO();
        try {
            int i = usersService.addUser(user);
            if (i > 0){
                resultVO.setCode(0);
                resultVO.setMsg("添加成功");
            }
        }catch (Exception e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    /*删除*/
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO delUsers(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =usersService.delUsersById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
    /*修*/
    @GetMapping("/{id}")
    public String getUsersById(@PathVariable Integer id, Model model){
        User user = usersService.selectUserById(id);
        model.addAttribute("user",user);
        return "admin/users/usersEdit";
    }
    @PutMapping("")
    @ResponseBody
    public ResultVO updateNotice(User user){
        ResultVO resultVO = new ResultVO();
        try {
            int i = usersService.editUser(user);
            if (i>0){
                resultVO.setCode(0);
                resultVO.setMsg("修改成功");
            }
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
}
