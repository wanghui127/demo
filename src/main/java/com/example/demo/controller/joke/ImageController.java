package com.example.demo.controller.joke;

import com.example.demo.config.OSSConfig;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 图片
 */
@Controller
@RequestMapping("/image")
public class ImageController extends JsonResultUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(OSSController.class);

    @Autowired
    protected OSSConfig ossConfig;

    /**
     * 图片列表页面
     */
    @RequestMapping("/imageList")
    public String jokeList(){
        return "joke/imageList";
    }



    /**
     * 上传图片和提交表单
     * @param multipartFiles
     * @param params
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult uploadImage(@RequestParam("image") MultipartFile[] multipartFiles, @RequestBody Map<String, String> params, HttpServletRequest request){


        return this.successRender();
    }




}
