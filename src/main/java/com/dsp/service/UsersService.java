package com.dsp.service;

import com.dsp.domain.Building;
import com.dsp.domain.User;

import java.util.List;

public interface UsersService {

    User selectUserById(Integer id);

    List<User> getUserList();

    List<Building> getUserAndLike(User param);
}
