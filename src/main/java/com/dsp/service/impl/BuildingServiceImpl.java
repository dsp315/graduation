package com.dsp.service.impl;

import com.dsp.domain.Building;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.BuildingMapper;
import com.dsp.service.BuildingService;
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
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private BuildingMapper buildingMapper;

    /*查询所有*/
    @Override
    public List<Building> getBuildList() {
        List<Building> list = buildingMapper.selectAll();
        return list;
    }

    /*根据id查询*/
    @Override
    public Building getBuildById(Integer id){
        Building building = buildingMapper.selectByPrimaryKey(id);
        return building;
    }


    /*模糊查询*/
    @Override
    public List<Building> getBuildAndLike(Building param) {
        List<Building> list = new ArrayList<>();
        Example example = new Example(Building.class);
        Example.Criteria criteria = example.createCriteria();
        //获取名字
        criteria.andLike("bName","%" + param.getBName() +"%");
        list =buildingMapper.selectByExample(example);
        if (list.size() ==0){
            throw new MyException(MyEnum.BUILDING_NOT_EXIST);
        }
        return list;
    }

    /*添加*/
    @Override
    public int addBuild(Building building) {
        // 开启事务  start
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);


        int i = 0;
        try {
            i = buildingMapper.insertSelective(building);
            if (i > 0){
                //提交
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.BUILDING_ADD_FAIL);
        }
        return i;
    }

    /*删除*/
    @Override
    public int delBuildById(String strIds) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for  ( int  i  =   0 ; i  < ids.length; i ++ ){
                int id = Integer.parseInt(ids[i]);
                buildingMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.BUILDING_DELETE_FAIL);
        }
        return count;
    }
    /*修改*/
    @Override
    public int updateBuild(Building building){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i = buildingMapper.updateByPrimaryKeySelective(building);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.BUILDING_UPDATE_FAIL);
        }
        return i;
    }

}
