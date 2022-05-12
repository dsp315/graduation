package com.dsp.controller;

import com.dsp.domain.Car;
import com.dsp.excetion.MyException;
import com.dsp.service.CarService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("")
    public String toCarUI(){
        return "admin/car/carList";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultVO getCarList(Car param){
        ResultVO resultVO = new ResultVO();
        if (param.getCName() == null){
            /*查询所有*/
            try {
                List<Car> list = carService.getCarList();
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            }catch (MyException e){
                resultVO.setMsg(e.getMessage());
            }
        }else {
            /*模糊查询*/
            try {
                List<Car> list = carService.getCarAndLike(param);
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
    @PostMapping("")
    @ResponseBody
    public ResultVO<Object> addCar(Car car){
        ResultVO resultVO = new ResultVO();
        try {
            int i = carService.addCar(car);
            resultVO.setCode(0);
            resultVO.setMsg("添加车位成功");

        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }

    /*删除*/
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO<Object> delCar(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =carService.delCarById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    /*根据id查询*/
    @GetMapping("/{id}")
    public String getCarById(@PathVariable Integer id, Model model){
        Car car = carService.getCarById(id);
        model.addAttribute("car",car);
        return "admin/car/carEdit";
    }
    /*修改*/
    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updateCar(Car car){
        ResultVO resultVO =new ResultVO();
        try {
            int i = carService.updateCar(car);
            resultVO.setCode(0);
            resultVO.setMsg("修改成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;

    }

    /*跳转到添加页面*/
    @GetMapping("/add/ui")
    public String toCarAdd(){
        return "admin/car/carAdd";
    }

}

