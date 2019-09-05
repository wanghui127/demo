package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;
/**
 * @program: demo
 * @description: OSS基础配置信息
 * @author: Hui.Wang
 * @Time: $
 * @create: 2019-08-01 21:43
 **/
@Component
@Data
public class OSSConfig implements Serializable {

    private static final long serialVersionUID = -119396871324982279L;

    /**
     * 阿里云 oss 站点
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 阿里云 oss 资源访问图片 picurl
     */
    @Value("${oss.picurlPrefix}")
    private String picurlPrefix;

    /**
     * 阿里云 oss 公钥
     */
    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 阿里云 oss 私钥
     */
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 阿里云 oss 文件根目录
     */
    @Value("${oss.bucketName}")
    private String bucketName;

}

