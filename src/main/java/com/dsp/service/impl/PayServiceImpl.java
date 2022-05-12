package com.dsp.service.impl;

import com.dsp.domain.Building;
import com.dsp.domain.Pay;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.PayMapper;
import com.dsp.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    PayMapper payMapper;
    /*查询所有*/
    @Override
    public List<Pay> getPayList() {
        return payMapper.selectAll();
    }
    /*根据id查询*/
    @Override
    public Pay getPayById(Integer id) {
        Pay pay = payMapper.selectByPrimaryKey(id);
        return pay;
    }
    /*模糊查询*/
    @Override
    public List<Pay> getPayAndLike(Pay param) {
        List<Pay> list = new ArrayList<>();
        Example example = new Example(Pay.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("pName","%" + param.getPName() +"%");
        list =payMapper.selectByExample(example);
        if (list.size() ==0){
            throw new MyException(MyEnum.PAY_NOT_EXIST);
        }
        return list;
    }


    /*添加*/
    @Override
    public int addPay(Pay pay) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            i = payMapper.insertSelective(pay);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.PAY_ADD_FAIL);
        }
        return i;
    }

    /*删除*/
    @Override
    public int delPayById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for  ( int  i  =   0 ; i  < ids.length; i ++ ){
                int id = Integer.parseInt(ids[i]);
                payMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.PAY_DELETE_FAIL);
        }
        return count;
    }

    /*修改*/
    @Override
    public int updatePay(Pay pay) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i = payMapper.updateByPrimaryKeySelective(pay);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (MyException e){
            txManager.rollback(status);
            throw new MyException(MyEnum.PAY_UPDATE_FAIL);
        }
        return i;
    }
}
