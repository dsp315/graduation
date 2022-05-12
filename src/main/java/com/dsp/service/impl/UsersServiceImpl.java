package com.dsp.service.impl;

import com.dsp.domain.Building;
import com.dsp.domain.User;
import com.dsp.mapper.UsersMapper;
import com.dsp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper userMapper;


    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public List<Building> getUserAndLike(User param) {
        return null;
    }
}
