package com.dsp.service.impl;

import com.dsp.domain.Building;
import com.dsp.domain.Unit;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.UnitMapper;
import com.dsp.service.BuildingService;
import com.dsp.service.UnitSerivce;
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
public class UnitServiceImpl implements UnitSerivce {

    @Autowired
    private PlatformTransactionManager txManager;

    @Resource
    UnitMapper unitMapper;

    /*查询所有*/
    @Override
    public List<Unit> getUnitList() {
        return unitMapper.selectListUnit();
    }

    /*根据id查询*/
    @Override
    public Unit getUnitById(Integer id) {
        return unitMapper.selectUnitById(id);
    }

    /*模糊查询*/
    @Override
    public List<Unit> getUnitAndLike(Unit param) {
        List<Unit> list = new ArrayList<>();

        list = unitMapper.selectUnitByNameAndLike(param);

        if (list.size() == 0){
            throw new MyException(MyEnum.UNIT_NOT_EXIST);
        }
        return list;
    }

    /*增*/
    @Override
    public int addUnit(Unit unit) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            String unitName = unit.getTName();
            Integer bId = unit.getBuild().getBId();
            i = unitMapper.insertUnit(unitName,bId);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.UNIT_ADD_FAIL);
        }
        return i;
    }

    /*删*/
    @Override
    public int delUnitById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for  ( int  i  =   0 ; i  < ids.length; i ++ ){
                int id = Integer.parseInt(ids[i]);
                unitMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.UNIT_DELETE_FAIL);
        }
        return count;
    }

    /*改*/
    @Override
    public int updateUnit(Unit unit) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            i = unitMapper.updateByPrimaryKeySelective(unit);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.UNIT_UPDATE_FAIL);
        }
        return i;
    }
}
