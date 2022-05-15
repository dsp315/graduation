package com.dsp.controller;

import com.dsp.domain.Car;
import com.dsp.domain.Room;
import com.dsp.domain.User;
import com.dsp.excetion.MyException;
import com.dsp.service.*;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResultVO updateInfo(User user){
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
    /*停用车位*/
    @PutMapping("/delCar")
    @ResponseBody
    public ResultVO delCar(Integer id, Integer carId){
        ResultVO resultVO = new ResultVO();
        try {
            int i = usersService.delCar(id,carId);
            if (i>0){
                resultVO.setCode(0);
                resultVO.setMsg("停用车位成功");
            }
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }

        return resultVO;
    }
    /*停用房屋*/
    @PutMapping("/delRoom")
    @ResponseBody
    public ResultVO delRoom(Integer id, Integer roomId){
        ResultVO resultVO = new ResultVO();
        try {
            int i = usersService.delRoom(id,roomId);
            if (i>0){
                resultVO.setCode(0);
                resultVO.setMsg("停用房屋成功");
            }
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
    /*分配车位*/
    @Autowired
    CarService carService;
    //跳转页面
    @GetMapping("/createCarUI/{uid}")
    public String createCarUI(@PathVariable Integer uid, Model model){
        User user = usersService.selectUserById(uid);
        model.addAttribute("user",user);
        model.addAttribute("cars",carService.getCarList());
        return "admin/users/createCar";
    }
    //分配车位
    @PutMapping("/createCar")
    @ResponseBody
    public ResultVO createCar(User user){
        ResultVO resultVO = new ResultVO();
        try {
            Integer uId = user.getUId();
            Integer carId = user.getCarId();
            int i = usersService.createCar(uId,carId);
            if (i>0){
                resultVO.setCode(0);
                resultVO.setMsg("分配车位成功");
            }
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    /*分配房屋*/
    @Autowired
    BuildingService buildingService;
    @Autowired
    UnitSerivce unitSerivce;
    @Autowired
    RoomService roomService;
    //跳转到房屋页面
    @GetMapping("/createRoomUI/{id}")
    public String createRoomUI(@PathVariable Integer id, Model model){
        User user = usersService.selectUserById(id);
        model.addAttribute("rooms",roomService.getRoomListByStateSetZero());
        model.addAttribute("user",user);
        return "admin/users/createRoom";
    }
    //分配房屋
    @PutMapping("/createRoom")
    @ResponseBody
    public ResultVO createRoom(User user){
        ResultVO resultVO = new ResultVO();
        try {
            Integer uId = user.getUId();
            Integer roomId = user.getRoomId();
            int i = usersService.createRoom(uId,roomId);
            if (i>0){
                resultVO.setCode(0);
                resultVO.setMsg("分配房屋成功");
            }
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }
    /*缴费*/
    @Autowired
    PayService payService;
    //跳转到缴费页面
    @GetMapping("/createUserPayUI/{id}")
    public String createUserPayUI(@PathVariable Integer id, Model model){
        model.addAttribute("pays",payService.getPayList());
        model.addAttribute("userId",id);
        return "admin/users/createUserPay";
    }
}
