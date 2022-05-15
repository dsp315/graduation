package com.dsp.service;

import com.dsp.domain.Room;
import com.dsp.domain.Unit;

import java.util.List;

public interface RoomService {
    List<Room> getRoomList();

    List<Room> getRoomAndLike(Room param);

    List<Room> getRoomListByStateSetZero();

    int addRoom(Room room);

    int delRoomById(String ids);

    Room getRoomById(Integer id);

    int updateRoom(Room room);

}
