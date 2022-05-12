package com.dsp.service.impl;

import com.dsp.domain.Building;
import com.dsp.domain.Car;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.CarMapper;
import com.dsp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private PlatformTransactionManager txManager;

    @Resource
    CarMapper carMapper;


    /*查询所有*/
    @Override
    public List<Car> getCarList() {
        return carMapper.selectAll();
    }


    /*根据id查询*/
    @Override
    public Car getCarById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    /*模糊查询*/
    @Override
    public List<Car> getCarAndLike(Car param) {
        List<Car> list = new ArrayList<>();
        Example example = new Example(Car.class);
        Example.Criteria criteria = example.createCriteria();
        //获取名字
        criteria.andLike("cName","%" + param.getCName() +"%");
        list =carMapper.selectByExample(example);
        if (list.size() ==0){
            throw new MyException(MyEnum.CAR_NOT_EXIST);
        }
        return list;
    }


    /*添加*/
    @Override
    public int addCar(Car car) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            car.getCId();
            car.getCName();

            i = carMapper.insertSelective(car);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.CAR_ADD_FAIL);
        }
        return i;
    }

    /*删除*/
    @Override
    public int delCarById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for  ( int  i  =   0 ; i  < ids.length; i ++ ){
                int id = Integer.parseInt(ids[i]);
                carMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.CAR_DELETE_FAIL);
        }
        return count;
    }

    /*修改*/
    @Override
    public int updateCar(Car car) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i = carMapper.updateByPrimaryKeySelective(car);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.CAR_UPDATE_FAIL);
        }
        return i;
    }

}
