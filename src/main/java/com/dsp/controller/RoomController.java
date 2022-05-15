package com.dsp.controller;

import com.dsp.domain.Building;
import com.dsp.domain.Room;
import com.dsp.domain.Unit;
import com.dsp.excetion.MyException;
import com.dsp.service.BuildingService;
import com.dsp.service.RoomService;
import com.dsp.service.UnitSerivce;
import com.dsp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("")
    public String toUnitUI(){
        return "admin/room/roomList";
    }
    /*查询所有*/
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getRoomList(Room param) {
        ResultVO resultVO = new ResultVO();
        if(param.getRName() != null || param.getStartNum() != null || param.getEndNum() != null) {
            /*模糊查询*/
            try {
                List<Room> list = roomService.getRoomAndLike(param);
                resultVO.setData(list);
                resultVO.setCode(0);
                resultVO.setCount((long) list.size());
            } catch (MyException e) {
                resultVO.setMsg(e.getMessage());
            }
        } else{
            /*查询所有*/
            try {
                List<Room> list = roomService.getRoomList();
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
    public ResultVO<Object> delRoom(@PathVariable String ids){
        ResultVO resultVO = new ResultVO();
        try {
            int i =roomService.delRoomById(ids);
            resultVO.setCode(0);
            String message = "成功删除" + i + "条记录";
            resultVO.setMsg(message);
        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return resultVO;
    }

    @Autowired
    UnitSerivce unitSerivce;
    @Autowired
    BuildingService buildingService;

    /*根据id查询并跳转到修改页面*/
    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Integer id, Model model){
        Room room = roomService.getRoomById(id);
        model.addAttribute("room",room);
        model.addAttribute("units",unitSerivce.getUnitList());
        model.addAttribute("buildings",buildingService.getBuildList());
        return "admin/room/roomEdit";
    }
    /*根据楼栋id查询楼栋名字，并返回给修改页面*/
    @GetMapping("/selectBuildType")
    @ResponseBody
    public Integer selectBuildType(Integer uid){
        Unit unit = unitSerivce.getUnitById(uid);
        Integer tid = unit.getBuildingId();
        Building build = buildingService.getBuildById(tid);
        return build.getBType();
    }

    /*修改*/
    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updateRoom(Room room){
        ResultVO resultVO =new ResultVO();
        try {
            int i = roomService.updateRoom(room);
            resultVO.setCode(0);
            resultVO.setMsg("修改成功");
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
    public String toRoomAdd(Model model){
        model.addAttribute("units",unitSerivce.getUnitList());
        return "admin/room/roomAdd";
    }

    /*添加*/
    @PostMapping("")
    @ResponseBody
    public ResultVO<Object> addRoom(Room room){
        ResultVO resultVO = new ResultVO();
        try {
            int i = roomService.addRoom(room);
            resultVO.setCode(0);
            resultVO.setMsg("添加房间成功");

        }catch (MyException e){
            resultVO.setMsg(e.getMessage());
        }
        return  resultVO;
    }
}
