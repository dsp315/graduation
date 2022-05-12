package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoomMapper extends BaseTkMapper<Room> {
    List<Room> selectListRoom();
    //根据id查找
    Room selectRoomById(@Param("id") Integer id);
    //根据名字模糊查询
    List<Room> selectRoomByNameAndLike(Room room);
    //添加
    //int insertRoom(@Param("roomName") String roomName,@Param("bId") Integer bId);

}
