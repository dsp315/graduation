package com.dsp.service.impl;

import com.dsp.domain.Maintain;
import com.dsp.domain.User;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.MaintainMapper;
import com.dsp.mapper.UsersMapper;
import com.dsp.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class MaintainServiceImpl implements MaintainService {

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    MaintainMapper maintainMapper;

    @Autowired
    UsersMapper userMapper;

    @Override
    public List<Maintain> getMaintainAndLike(Maintain param) {
        List<Maintain> maintains = maintainMapper.selectMaintainAndLike(param);

        //遍历循环获取各自的user放到属性User
        for (Maintain maintain : maintains) {
            //根据userid获取user
            User user = userMapper.selectByPrimaryKey(maintain.getUserId());
            //将获取到的user放到maintain的属性类中
            maintain.setUser(user);
        }

        return maintains;
    }

    @Override
    public List<Maintain> getNoticeList() {
        List<Maintain> maintains = maintainMapper.selectMaintainList();
        //遍历循环获取各自的user放到属性User
        for (Maintain maintain : maintains) {
            //根据userid获取user
            User user = userMapper.selectByPrimaryKey(maintain.getUserId());
            //将获取到的user放到maintain的属性类中
            maintain.setUser(user);
        }
        return maintains;
    }

    @Override
    public Maintain getMaintainById(Integer id) {
        Maintain maintain = maintainMapper.selectById(id);
        User user = userMapper.selectByPrimaryKey(maintain.getUserId());
        maintain.setUser(user);
        return maintain;
    }
    /*根据用户id查询maintain*/

    @Override
    public List<Maintain> getMaintainListByUserId(Integer id) {
        return maintainMapper.selectMaintainListByUserId(id);

    }

    @Override
    public int delMaintainById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for (int i = 0; i<ids.length;i++) {
                int id = Integer.parseInt(ids[i]);
                maintainMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.MAINTAIN_DELETE_FAIL);
        }
        return count;
    }

    @Override
    public int addMaintain(Maintain maintain) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int count = 0;

        try {
            maintain.setMReleaseTime(new Date());
            count = maintainMapper.insertSelective(maintain);
            if (count>0){
                txManager.commit(status);
            }
        }catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.MAINTAIN_INSERT_FAIL);
        }
        return count;
    }
}
