package com.dsp.service.impl;

import com.dsp.domain.Admin;
import com.dsp.excetion.MyEnum;
import com.dsp.excetion.MyException;
import com.dsp.mapper.AdminMapper;
import com.dsp.service.AdminService;
import com.dsp.utils.FileUploadUtil;
import com.dsp.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PlatformTransactionManager txManager;

    @Resource
    AdminMapper adminMapper;

    /*登录*/
    @Override
    public Admin login(Admin param) {

        //密码加密
        String password = param.getAPassword();
        password = MD5Util.getMD5(password);
        param.setAPassword(password);

        List<Admin> list = adminMapper.select(param);

        if (list.size() == 0){
            throw new MyException(MyEnum.ADMIN_LOGIN_NOT_ACCOUNT);
        }
        return list.get(0);
    }

    @Override
    public Admin getAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int modifyAdminInfo(Admin admin) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);
        int i = 0;
        try {
            i = adminMapper.updateAdminInfo(admin);
            if (i > 0){
                //提交
                txManager.commit(status);
            }else{
                throw new MyException(MyEnum.ADMIN_MODIFY_INFO_FAIL);
            }
        }catch (MyException e){
            txManager.rollback(status);
        }
        return i;
    }

    /**
     * 照片上传
     */
    @Override
    public Map<String,String> editImg(MultipartFile fileUpload, String paramName, HttpServletRequest request) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = txManager.getTransaction(def);

        Map<String,String> map = new HashMap<>();

        try {
            //获取管理员信息，方便进行创建文佳佳
            Admin admin = (Admin) request.getSession().getAttribute("info");
            Integer aId = admin.getAId();
            String aName = admin.getAName();
            //拼接文件夹路径
            paramName = paramName + "/" + aId + "/" + aName;
            //写入文件
            String filePath = FileUploadUtil.uploadFile(fileUpload, paramName);
            //写进数据库中
            int i = adminMapper.editImg(aId,filePath);
            if (i>0){
                //提交
                txManager.commit(status);
                map.put("code","写入成功");
                map.put("filePath",filePath);
            }else {
                throw new MyException(MyEnum.ADMIN_MODIFY_IMG_FAIL);
            }
        }catch (MyException e){
            txManager.rollback(status);
        }
        return map;
    }
}
