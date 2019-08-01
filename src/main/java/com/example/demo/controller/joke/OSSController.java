package com.example.demo.controller.joke;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.example.demo.config.OSSConfig;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import com.example.demo.utils.OSSBootUtil;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

/**
 * OSS对象存储
 */
@Controller
@RequestMapping("oss/")
public class OSSController extends JsonResultUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(OSSController.class);

    @Autowired
    private OSSConfig ossConfig;


    @PostMapping(value = "/upload")
    @ResponseBody
    public JsonResult ossTest(){

        return this.successRender();
    }


}
