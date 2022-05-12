package com.dsp.service.impl;

import com.dsp.domain.Guestbook;
import com.dsp.domain.User;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.GuestbookMapper;
import com.dsp.mapper.UsersMapper;
import com.dsp.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class GuestbookServiceImpl implements GuestbookService {
    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    GuestbookMapper guestbookMapper;

    @Autowired
    UsersMapper userMapper;

    @Override
    public List<Guestbook> getGuestbookAndLike(Guestbook param) {
        List<Guestbook> guestbooks = guestbookMapper.selectGuestbookListAndLike(param);
        if (guestbooks.size() != 0){
            //遍历循环获取各自的user放到属性User
            for (Guestbook guestbook : guestbooks) {
                //根据userid获取user
                User user = userMapper.selectByPrimaryKey(guestbook.getUserId());
                //将获取到的user放到maintain的属性类中
                guestbook.setUser(user);
            }
            return guestbooks;
        }else {
            throw new MyException(MyEnum.Guestbook_NOT_EXIST);
        }
    }

    @Override
    public List<Guestbook> getGuestbookList() {

        List<Guestbook> guestbooks = guestbookMapper.selectGuestbookList();
        //遍历循环获取各自的user放到属性User
        for (Guestbook guestbook : guestbooks) {
            //根据userid获取user
            User user = userMapper.selectByPrimaryKey(guestbook.getUserId());
            //将获取到的user放到maintain的属性类中
            guestbook.setUser(user);
        }
        return guestbooks;
    }

    @Override
    public Guestbook getGuestbookById(Integer id) {
        Guestbook guestbook = guestbookMapper.selectById(id);
        User user = userMapper.selectByPrimaryKey(guestbook.getUserId());
        guestbook.setUser(user);
        return guestbook;
    }

    @Override
    public int delGuestbookById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for (int i = 0; i<ids.length;i++) {
                int id = Integer.parseInt(ids[i]);
                guestbookMapper.deleteById(id);
                count++;
            }
            if (count > 0) {
                txManager.commit(status);
            }
            return count;
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.Guestbook_DELETE_FAIL);
        }
    }
}
