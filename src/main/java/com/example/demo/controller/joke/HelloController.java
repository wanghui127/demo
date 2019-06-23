package com.example.demo.controller.joke;

import com.example.demo.entity.joke.Joke;
import com.example.demo.service.joke.JokeService;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: demo
 * @description: 启动测试
 * @author: Hui.Wang
 * @Time: new Date()$
 * @create: 2019-06-19 20:41
 **/
@Controller
public class HelloController extends JsonResultUtil{

    @Autowired
    JokeService jokeService;

    //templates下的页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //templates下的页面
    @RequestMapping("/jokeList")
    public String jokeList(){
        return "joke/jokeList";
    }


    /** 
    * @Description:  数据库连接测试
     * @Param:
    * @return:  
    * @Author: Hui.Wang 
    * @Date: 2019/6/19 0019 
    * @Time: 22:20 
    */ 
    @RequestMapping(value = "/joke",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectJoke(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        int total = jokeService.selectCount(params);
        //页数
        int page =Integer.valueOf(request.getParameter("page"));
        //显示的条数
        int limit =Integer.valueOf(request.getParameter("limit"));
        int firstNum = (page-1)*limit;
        params.put("lastNum",String.valueOf(limit));
        params.put("firstNum",String.valueOf(firstNum));
        //查询的数据集
        List<Joke> jokes = jokeService.selectPage(params);
        return this.successRender().add("data",jokes).add("status",0).add("total",total).add("msg","成功");
    }
}
