package com.dsp.service.impl;

import com.dsp.domain.Car;
import com.dsp.domain.Room;
import com.dsp.domain.User;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.CarMapper;
import com.dsp.mapper.RoomMapper;
import com.dsp.mapper.UsersMapper;
import com.dsp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    PlatformTransactionManager txManager;

    @Autowired
    UsersMapper userMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    RoomMapper roomMapper;


    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserList() {
        List<User> users = userMapper.selectAll();
        for (int i = 0;i<users.size();i++){
            User user = users.get(i);
            //获取车位信息
            Integer carId = user.getCarId();
            Car car = carMapper.selectByPrimaryKey(carId);
            user.setCar(car);
            //获取房屋信息
            Integer roomId = user.getRoomId();
            Room room = roomMapper.selectRoomById(roomId);
            user.setRoom(room);
        }
        return users;
    }

    @Override
    public List<User> getUserAndLike(User param) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("uName","%"+ param.getUName() + "%");
        criteria.orLike("uPhone","%" + param.getUPhone() + "%");
        List<User> users = userMapper.selectByExample(example);
        for (User user: users) {
            //获取车位信息
            Integer carId = user.getCarId();
            Car car = carMapper.selectByPrimaryKey(carId);
            user.setCar(car);
            //获取房屋信息
            Integer roomId = user.getRoomId();
            Room room = roomMapper.selectRoomById(roomId);
            user.setRoom(room);
        }
        return users;
    }

    @Override
    public int addUser(User user) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i =userMapper.insertSelective(user);
            if (i>0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.USER_ADD_FAIL);
        }
        return i;
    }
    /*删*/
    @Override
    public int delUsersById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);
                userMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.USER_DELETE_FAIL);
        }
        return count;
    }

    @Override
    public int editUser(User user) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i = userMapper.updateByPrimaryKeySelective(user);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (Exception e){
            txManager.rollback(status);
            throw new MyException(MyEnum.USER_UPDATE_FAIL);
        }
        return i;
    }
}
