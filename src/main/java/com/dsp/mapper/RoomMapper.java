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

    //修改状态为无主
    int updateRoomSetZeroById(Integer rId);

    //修改状态为有主
    int updateRoomStateSetOne(Integer roomId);

    //查询无主的房屋
    List<Room> selectRoomListByStateSetZero();
}
