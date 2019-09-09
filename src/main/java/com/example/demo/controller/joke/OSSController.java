package com.example.demo.controller.joke;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.example.demo.config.OSSConfig;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import com.example.demo.utils.OSSBootUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * OSS对象存储
 */
@Controller
@RequestMapping("/oss")
public class OSSController extends JsonResultUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(OSSController.class);

    @Autowired
    protected OSSConfig ossConfig;


    private static FileItem createFileItem(String filePath) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "text/plain", true,
                "MyFileName" + extFile);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(newfile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }


    private static MultipartFile getMulFileByPath(String picPath) {
        FileItem fileItems = createFileItem(picPath);
        MultipartFile mfile = new CommonsMultipartFile(fileItems);
        return mfile;
    }

    @GetMapping(value = "/uploadTest")
    @ResponseBody
    public JsonResult ossTest() {
        String picsrc = "";
        picsrc = OSSBootUtil.upload(ossConfig, getMulFileByPath("F:\\图片\\LEGION\\微信图片_20190426112453.jpg"), "joke/image");
        return this.successRender().add("picsrc", picsrc);
    }


    public static void main(String[] args) {
        try {
            //断点续传测试
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = "http://oss-cn-beijing.aliyuncs.com";
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = "LTAIt7lc0CxAaTn9";
            String accessKeySecret = "nB0G37bNMLWAaUweT8K15wEHRV1jWp";

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            ObjectMetadata meta = new ObjectMetadata();
            // 指定上传的内容类型。
            meta.setContentType("text/plain");

            // 通过UploadFileRequest设置多个参数。
            UploadFileRequest uploadFileRequest = new UploadFileRequest("sparrow-test", "不良人.mp4");

            // 通过UploadFileRequest设置单个参数。
            // 设置存储空间名称。
            //uploadFileRequest.setBucketName("<yourBucketName>");
            // 设置文件名称。
            //uploadFileRequest.setKey("<yourObjectName>");
            // 指定上传的本地文件。
            uploadFileRequest.setUploadFile("F:/迅雷下载/画江湖之不良人3-01.mp4");
            // 指定上传并发线程数，默认为1。
            uploadFileRequest.setTaskNum(5);
            // 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
            uploadFileRequest.setPartSize(1 * 1024 * 1024);
            // 开启断点续传，默认关闭。
            uploadFileRequest.setEnableCheckpoint(true);
            // 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
            uploadFileRequest.setCheckpointFile("uploadFile.ucp");
            // 文件的元数据。
            uploadFileRequest.setObjectMetadata(meta);
            // 设置上传成功回调，参数为Callback类型。
            //uploadFileRequest.setCallback("<yourCallbackEvent>");

            // 断点续传上传。
            ossClient.uploadFile(uploadFileRequest);

            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}
