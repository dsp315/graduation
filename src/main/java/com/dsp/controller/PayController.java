package com.dsp.controller;

import com.dsp.domain.Building;
import com.dsp.domain.Pay;
import com.dsp.excetion.MyException;
import com.dsp.service.PayService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    PayService payService;

    /*跳转到list页面*/
    @GetMapping("")
    public String toPayUI(){
        return "admin/pay/payList";
    }
    /*查询*/
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getPayList(Pay param){
        ResultVO resultVO = new ResultVO();
        if (param.getPName() == null){
            /*查询所有*/
            try {
                List<Pay> list = payService.getPayList();
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            }catch (MyException e){
                resultVO.setMsg(e.getMessage());
            }
        }else {
            /*模糊查询*/
            try {
                List<Pay> list = payService.getPayAndLike(param);
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
    public ResultVO<Object> addPay(Pay pay){
        ResultVO resultVO = new ResultVO();
        try {
            int i = payService.addPay(pay);
            resultVO.setCode(0);
            resultVO.setMsg("添加缴费类型成功");

        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }

    /*删除*/
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO<Object> delPay(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =payService.delPayById(ids);
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
    public String getPayById(@PathVariable Integer id, Model model){
        Pay pay = payService.getPayById(id);
        model.addAttribute("pay",pay);
        return "admin/pay/payEdit";
    }
    /*修改*/
    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updatePay(Pay pay){
        ResultVO resultVO =new ResultVO();
        try {
            int i = payService.updatePay(pay);
            resultVO.setCode(0);
            resultVO.setMsg("修改成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;

    }

    /*跳转到添加页面*/
    @GetMapping("/add/ui")
    public String toPayAdd(){
        return "admin/pay/payAdd";
    }

}
