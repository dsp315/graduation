package com.dsp.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class FileUploadUtil {
    /**
     * 上传文件到缓存文件夹中并返回储存的相对路径的方法
     */
    public static String uploadFile(MultipartFile uploadFile, String paramName){
        //1-获取文件的原始姓名
        String originalFilename = uploadFile.getOriginalFilename();
        //2-获取项目的根路径
        // E:|mywork|target|classes|static|admin
        String basePath = "E:\\mywork\\target\\classes\\static\\admin\\";
        //3-获取存放文件的父路径
        String parentFilePath = paramName + "/";
        //4-判断父文件是否存在，不存在就创建，存在直接使用
        File parentFile = new File(basePath + parentFilePath);
        if (!parentFile.exists()){
            //5-如果不存在，就创建文件夹
            parentFile.mkdirs();
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");//年月日时分秒毫秒
        //6-将这个不会重复的时间作为文件名
        String fileName = simpleDateFormat.format(date);
        //7-文件上传
        try {
            //写入编写文件
            uploadFile.transferTo(new File(basePath+parentFilePath+fileName));//没有后缀名字
        }catch (IOException e){
            e.printStackTrace();
        }
        //upload/1/admin/20020501103623200
        return parentFilePath + fileName;
    }

    /**
     * 将缓存文件下载到编译文件中
     * copyFile 被复制的文件
     * downFile 需要下载的文件
     */
    public static void downloadFile(String paramName){
        //1-获取下载文件的根路径（下载到该文件中）
        // E:|mywork|src|main|resources|static|admin
        String downParentPath = "E:\\mywork\\src\\main\\resources\\static\\admin\\";
        //2-拼接下载文件路径
        String downPath = downParentPath + paramName;
        //3-获取存放文件的路径(目标文件)
        String copyParentPath = "E:\\mywork\\target\\classes\\static\\admin\\";
        //4-拼接目标文件
        String copyPath = copyParentPath + paramName;
        //5-创造文件
        File downFile = new File(downPath);
        File copyFile = new File(copyPath);
        //6-判断downFile文件是否存在
        if (!downFile.exists()){
            downFile.mkdirs();
        }
        //7-复制文件
        try {
            //将copyFile复制到downFile文件中
            FileUtils.copyDirectory(copyFile,downFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 将springboot前端上传的文件编码为base64格式字符串
     *
     * @param file 上传的文件
     * @return 编码后的字符串
     */
    static Base64.Encoder utilEncoder = Base64.getEncoder();

    public static String generateBase64(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "图片不能为空！";
        }
        byte[] imageBytes = null;
        String base64EncoderImg = "";
        try {
            imageBytes = file.getBytes();
            base64EncoderImg = utilEncoder.encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64EncoderImg;
    }
}
