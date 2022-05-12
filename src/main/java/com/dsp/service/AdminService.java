package com.dsp.service;

import com.dsp.domain.Admin;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AdminService {
    Admin login(Admin param);

    Admin getAdmin(Integer id);

    int modifyAdminInfo(Admin admin);

    Map<String,String> editImg(MultipartFile fileUpload, String paramName, HttpServletRequest request);
}
