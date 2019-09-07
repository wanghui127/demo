package com.example.demo.controller.joke;

import com.example.demo.config.OSSConfig;
import com.example.demo.entity.joke.JokeImage;
import com.example.demo.service.joke.ImageService;
import com.example.demo.service.joke.JokeService;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import com.example.demo.utils.OSSBootUtil;
import com.example.demo.utils.UUIDUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private ImageService imageService;

    /**
     * 图片列表页面跳转
     */
    @RequestMapping("/imageList")
    public String jokeList(){
        return "joke/imageList";
    }


    /**
     * 图片列表分页
     * @param request
     * @return
     */
    @PostMapping(value = "/image")
    @ResponseBody
    public JsonResult selectImage(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        int total = imageService.selectCount(params);
        //页数
        int page =Integer.valueOf(request.getParameter("page"));
        //显示的条数
        int limit =Integer.valueOf(request.getParameter("limit"));
        int firstNum = (page-1)*limit;
        params.put("lastNum",String.valueOf(limit));
        params.put("firstNum",String.valueOf(firstNum));
        //查询的数据集
        List<JokeImage> images = imageService.selectPage(params);
        return this.successRender().add("data",images).add("status",0).add("total",total).add("msg","成功");
    }



    /**
     * 上传图片和提交表单
     * @param multipartFile
     * @param
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult uploadImage(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request){
        System.err.println(multipartFile.getOriginalFilename());
        String picsrc  = "";
        try {
            picsrc =  OSSBootUtil.upload(ossConfig,multipartFile,"joke/image");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("上传图片错误！",e.getMessage());
            return this.failRender();
        }
        return this.successRender().add("message",picsrc);
    }


    @PostMapping("/insertImage")
    @ResponseBody
    public JsonResult insertImage(@RequestBody JSONObject params, HttpServletRequest request){
        Object[] images=params.getJSONArray("resultImages").toArray();
        for (Object a: images) {
            System.out.println(a);
        }

        String imageId = UUIDUtils.getUUID();
        JokeImage jokeImage = new JokeImage();
        jokeImage.setId(imageId);
        jokeImage.setFlag(0);
        jokeImage.setTitle("title");
        jokeImage.setUserId("");
        imageService.insert(jokeImage);
        return this.successRender();
    }


}
