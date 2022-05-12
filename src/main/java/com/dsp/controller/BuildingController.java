package com.dsp.controller;

import com.dsp.domain.Building;
import com.dsp.excetion.MyException;
import com.dsp.service.BuildingService;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    BuildingService buildingService;

    /*跳转到list页面*/
    @GetMapping("")
    public String toBuildingUI(){
        return "admin/building/buildList";
    }
    /*查询*/
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getBuildList(Building param){
        ResultVO resultVO = new ResultVO();
        if (param.getBName() == null){
            /*查询所有*/
            try {
                List<Building> list = buildingService.getBuildList();
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            }catch (MyException e){
                resultVO.setMsg(e.getMessage());
            }
        }else {
            /*模糊查询*/
            try {
                List<Building> list = buildingService.getBuildAndLike(param);
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
    public ResultVO<Object> addBuild(Building building){
        ResultVO resultVO = new ResultVO();
        try {
            int i = buildingService.addBuild(building);
            resultVO.setCode(0);
            resultVO.setMsg("添加楼栋成功");

        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }

    /*删除*/
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO<Object> delBuild(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =buildingService.delBuildById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录¼";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    /*根据id查询*/
    @GetMapping("/{id}")
    public String getBuildById(@PathVariable Integer id, Model model){
        Building building = buildingService.getBuildById(id);
        model.addAttribute("building",building);
        return "admin/building/buildEdit";
    }
    /*修改*/
    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updateBuild(Building building){
        ResultVO resultVO =new ResultVO();
        try {
            int i = buildingService.updateBuild(building);
            resultVO.setCode(0);
            resultVO.setMsg("修改成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;

    }

    /*跳转到添加页面*/
    @GetMapping("/add/ui")
    public String toBuildAdd(){
        return "admin/building/buildAdd";
    }

}
