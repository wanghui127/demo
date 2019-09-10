package com.example.demo.controller.joke;

import com.example.demo.entity.joke.ImageFile;
import com.example.demo.service.joke.ImageFileService;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import com.example.demo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片瀑布流展示
 */
@Controller
@RequestMapping("/imageFile")
public class ImageFileController extends JsonResultUtil {

    @Autowired
    private ImageFileService imageFileService;

    /**
     * 图片页面
     * @return
     */
    @RequestMapping("/page")
    public String imageFilePage(){
        return "joke/imageFileList";
    }


    @GetMapping("/list/{page}")
    @ResponseBody
    public JsonResult imageFileList(@PathVariable("page") Integer page , HttpServletRequest request, Map<String,String> pp){
        Map<String, String> params = new HashMap<>();
        int total = imageFileService.countByFlag(0);
        //页数
        //int page = Integer.valueOf(request.getParameter("page"));
        //显示的条数,默认显示10条
        int limit = 10;
        if (StringUtil.isNotEmpty(request.getParameter("limit"))){
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        int firstNum = (page - 1) * limit;
        params.put("lastNum", String.valueOf(limit));
        params.put("firstNum", String.valueOf(firstNum));
        //查询的数据集
        List<ImageFile> ImageFiles = imageFileService.selectPage(params);
        //pages为返回的总页数，可以计算出来
        int pages = (total-1)/limit+1;
        return this.successRender().add("data", ImageFiles).add("status", 0).add("total", total).add("msg", "成功").add("pages",pages);
    }


}
