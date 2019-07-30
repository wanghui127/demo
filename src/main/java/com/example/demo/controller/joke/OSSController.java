package com.example.demo.controller.joke;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;

import java.io.*;

/**
 * OSS对象存储
 */
public class OSSController {

    //上传文件
    public static void uploadFile(){

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        //切记不可泄露
        String accessKeyId = "LTAIt7lc0CxAaTn9";
        String accessKeySecret = "nB0G37bNMLWAaUweT8K15wEHRV1jWp";
        String bucketName = "sparrow-test";
        String objectName = "HelloOSS.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        // 关闭OSSClient
        ossClient.shutdown();
    }

    //下载文件
    public static void downloadFile() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIt7lc0CxAaTn9";
        String accessKeySecret = "nB0G37bNMLWAaUweT8K15wEHRV1jWp";
        String bucketName = "sparrow-test";
        String objectName = "HelloOSS.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void main(String[] args) {

        //上传文件
        //uploadFile();

        try {
            //下载文件
            downloadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
