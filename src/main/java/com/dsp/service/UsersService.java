package com.dsp.service;

import com.dsp.domain.Building;
import com.dsp.domain.User;

import java.util.List;

public interface UsersService {

    User selectUserById(Integer id);

    List<User> getUserList();

    List<User> getUserAndLike(User param);

    int addUser(User user);

    int delUsersById(String ids);

    int editUser(User user);
}
