package com.dsp.service.impl;

import com.dsp.domain.Admin;
import com.dsp.domain.Notice;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.AdminMapper;
import com.dsp.mapper.NoticeMapper;
import com.dsp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private PlatformTransactionManager txManager;

    @Resource
    NoticeMapper noticeMapper;

    @Resource
    AdminMapper adminMapper;

    @Override
    public List<Notice> getNoticeList() {
        List<Notice> list = noticeMapper.selectNoticeList();
        if (list.size() !=0){
            //遍历list，通过外键获取admin的信息，并赋值给各个属性
            for (int i = 0; i<list.size();i++){
                Notice notice = list.get(i);
                //获取修改者的外键id
                Integer adminMoId = notice.getAdminMoId();
                //获取修改者管理员信息，并赋值给AdminMo
                Admin adminMo = adminMapper.selectByPrimaryKey(adminMoId);
                notice.setAdminMo(adminMo);
                //将发布者信息获取，并赋值给AdminPu
                Integer adminPuId = notice.getAdminPuId();
                Admin adminPu = adminMapper.selectByPrimaryKey(adminPuId);
                notice.setAdminPu(adminPu);
            }
        }
        return list;
    }
    /*查询已发布的公告*/
    @Override
    public List<Notice> getNoticeStateSetOne() {
        List<Notice> list = noticeMapper.selectNoticeStateSetOne();
        if (list.size() !=0){
            //遍历list，通过外键获取admin的信息，并赋值给各个属性
            for (int i = 0; i<list.size();i++){
                Notice notice = list.get(i);
                //获取修改者的外键id
                Integer adminMoId = notice.getAdminMoId();
                //获取修改者管理员信息，并赋值给AdminMo
                Admin adminMo = adminMapper.selectByPrimaryKey(adminMoId);
                notice.setAdminMo(adminMo);
                //将发布者信息获取，并赋值给AdminPu
                Integer adminPuId = notice.getAdminPuId();
                Admin adminPu = adminMapper.selectByPrimaryKey(adminPuId);
                notice.setAdminPu(adminPu);
                //将发布日期格式化
            }
        }
        return list;
    }
    /*分页查询已发布的公告*/
    @Override
    public List<Notice> getNoticeStateSetOne(long current,int pageTotal) {

        long page = (current -1 ) * 5;
        List<Notice> list = noticeMapper.selectNoticeStateSetOnePage(page,pageTotal);
        if (list.size() !=0){
            //遍历list，通过外键获取admin的信息，并赋值给各个属性
            for (int i = 0; i<list.size();i++){
                Notice notice = list.get(i);
                //获取修改者的外键id
                Integer adminMoId = notice.getAdminMoId();
                //获取修改者管理员信息，并赋值给AdminMo
                Admin adminMo = adminMapper.selectByPrimaryKey(adminMoId);
                notice.setAdminMo(adminMo);
                //将发布者信息获取，并赋值给AdminPu
                Integer adminPuId = notice.getAdminPuId();
                Admin adminPu = adminMapper.selectByPrimaryKey(adminPuId);
                notice.setAdminPu(adminPu);
                //将发布日期格式化
            }
        }
        return list;
    }

    @Override
    public Notice getNoticeById(Integer id) {
        Notice notice = noticeMapper.selectNoticeById(id);
        //获取修改者的外键id
        Integer adminMoId = notice.getAdminMoId();
        //获取修改者管理员信息，并赋值给AdminMo
        Admin adminMo = adminMapper.selectByPrimaryKey(adminMoId);
        notice.setAdminMo(adminMo);
        //将发布者信息获取，并赋值给AdminPu
        Integer adminPuId = notice.getAdminPuId();
        Admin adminPu = adminMapper.selectByPrimaryKey(adminPuId);
        notice.setAdminPu(adminPu);
        return notice;
    }

    @Override
    public List<Notice> getNoticeAndLike(Notice param) {
        List<Notice> list = noticeMapper.selectNoticeAndLike(param);
        if (list.size() == 0){
            throw new MyException(MyEnum.NOTICE_NOT_EXIST);
        }else {
            //遍历list，通过外键获取admin的信息，并赋值给各个属性
            for (int i = 0; i<list.size();i++){
                Notice notice = list.get(i);
                //获取发布者的外键id
                Integer adminMoId = notice.getAdminMoId();
                //获取发布者管理员信息，并赋值给AdminMo
                Admin adminMo = adminMapper.selectByPrimaryKey(adminMoId);
                notice.setAdminMo(adminMo);
                //将修改者信息获取，并赋值给AdminPu
                Integer adminPuId = notice.getAdminPuId();
                Admin adminPu = adminMapper.selectByPrimaryKey(adminPuId);
                notice.setAdminPu(adminPu);
            }
            return list;
        }
    }
    /*删*/
    @Override
    public int delNoticeById(String strIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int count = 0;
        try {
            final String[] ids = strIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);
                noticeMapper.deleteByPrimaryKey(id);
                count++;
            }
            if (count > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.NOTICE_DELETE_FAIL);
        }
        return count;
    }

    /*增*/
    @Override
    public int addNotice(Notice notice) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        int i = 0;
        try {
            i = noticeMapper.insertSelective(notice);
            if (i > 0) {
                txManager.commit(status);
            }
        } catch (MyException e) {
            txManager.rollback(status);
            throw new MyException(MyEnum.NOTICE_ADD_FAIL);
        }
        return i;
    }

    /*改*/

    @Override
    public int updateNotice(Notice notice) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i = noticeMapper.updateByPrimaryKeySelective(notice);
            if (i > 0){
                txManager.commit(status);
            }
        }catch (Exception e){
            txManager.rollback(status);
            throw new MyException(MyEnum.NOTICE_UPDATE_FAIL);
        }
        return i;
    }

}
