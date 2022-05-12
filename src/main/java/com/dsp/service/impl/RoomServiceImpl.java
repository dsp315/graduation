package com.dsp.service.impl;

import com.dsp.domain.Room;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.RoomMapper;
import com.dsp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private PlatformTransactionManager txManager;

    @Resource
    RoomMapper roomMapper;

    /*查询所有*/
    @Override
    public List<Room> getRoomList() {
        return roomMapper.selectListRoom();
    }

    /*根据id查询*/
    @Override
    public Room getRoomById(Integer id) {
        return roomMapper.selectRoomById(id);
    }

    /*模糊查询*/
    @Override
    public List<Room> getRoomAndLike(Room param) {
        List<Room> list = new ArrayList<>();

        list = roomMapper.selectRoomByNameAndLike(param);

        if (list.size() == 0) {
            throw new MyException(MyEnum.ROOM_NOT_EXIST);
        }
        return list;
    }

    /*增*/
    @Override
    public int addRoom(Room room) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            i = roomMapper.insertSelective(room);
            if (i > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.ROOM_ADD_FAIL);
        }
        return i;
    }

    /*删*/
    @Override
    public int delRoomById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);
                roomMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.ROOM_DELETE_FAIL);
        }
        return count;
    }

    /*改*/
    @Override
    public int updateRoom(Room room) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            i = roomMapper.updateByPrimaryKeySelective(room);
            if (i > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.ROOM_UPDATE_FAIL);
        }
        return i;
    }
}
