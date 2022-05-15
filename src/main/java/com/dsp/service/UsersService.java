package com.dsp.service;

import com.dsp.domain.Car;
import com.dsp.domain.Room;
import com.dsp.domain.User;

import java.util.List;

public interface UsersService {

    User selectUserById(Integer id);

    List<User> getUserList();

    List<User> getUserAndLike(User param);

    int addUser(User user);

    int delUsersById(String ids);

    int editUser(User user);

    int delCar(Integer id, Integer carId);

    int delRoom(Integer id, Integer roomId);

    int createCar(Integer uId ,Integer cId);

    int createRoom(Integer uId,Integer rId);

}
