package com.dsp.controller;

import com.dsp.domain.Building;
import com.dsp.domain.Pay;
import com.dsp.domain.Unit;
import com.dsp.excetion.MyException;
import com.dsp.service.BuildingService;
import com.dsp.service.UnitSerivce;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.util.List;

@Controller
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    UnitSerivce unitSerivce;

    @GetMapping("")
    public String toUnitUI(){
        return "admin/unit/unitList";
    }
    /*查询所有*/
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getUnitList(Unit param) {
        ResultVO resultVO = new ResultVO();
        if (param.getTName() == null) {
            /*查询所有*/
            try {
                List<Unit> list = unitSerivce.getUnitList();
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            } catch (MyException e) {
                resultVO.setMsg(e.getMessage());
            }
        } else {
            /*模糊查询*/
            try {
                List<Unit> list = unitSerivce.getUnitAndLike(param);
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
    public ResultVO<Object> delUnit(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =unitSerivce.delUnitById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    @Autowired
    BuildingService buildingService;

    /**
     * 添加
     */
    /*跳转到添加页面*/
    @GetMapping("/add/ui")
    public String toRoomAdd(Model model){
        List<Building> buildList = buildingService.getBuildList();
        model.addAttribute("buildList",buildList);
        return "admin/unit/unitAdd";
    }

    /*添加*/
    @PostMapping("")
    @ResponseBody
    public ResultVO<Object> addUnit(HttpServletRequest request){
        ResultVO resultVO = new ResultVO();
        try {
            Integer bId = Integer.valueOf(request.getParameter("bId"));
            String tName = request.getParameter("tName");
            Building build = new Building();
            build.setBId(bId);
            Unit unit = new Unit();
            unit.setTName(tName);
            unit.setBuild(build);
            int i = unitSerivce.addUnit(unit);
            resultVO.setCode(0);
            resultVO.setMsg("添加单元成功");

        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }

    /**
     * 修改
     */
    /*根据id查询并跳转到修改页面*/
    @GetMapping("/{id}")
    public String getUnitById(@PathVariable Integer id, Model model){
        Unit unit = unitSerivce.getUnitById(id);
        model.addAttribute("unit",unit);
        model.addAttribute("buildings",buildingService.getBuildList());
        return "admin/unit/unitEdit";
    }
    /*根据楼栋id查询楼栋名字，并返回给修改页面*/
    @GetMapping("/selectBuildType")
    @ResponseBody
    public Integer selectBuildType(Integer bid){
        Building build = buildingService.getBuildById(bid);
        return build.getBType();
    }
    /*修改*/
    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updateUnit(Unit unit){
        ResultVO resultVO =new ResultVO();
        try {
            int i = unitSerivce.updateUnit(unit);
            resultVO.setCode(0);
            resultVO.setMsg("修改成功");
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;

    }

}
