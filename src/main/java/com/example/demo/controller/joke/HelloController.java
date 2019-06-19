package com.example.demo.controller.joke;

import com.example.demo.entity.joke.Joke;
import com.example.demo.service.joke.JokeService;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "index.html";
    }


    @RequestMapping("/joke")
    @ResponseBody
    public JsonResult selectJoke(){
        Map<String,String> params = new HashMap<>();
        List<Joke> jokes = jokeService.selectPage(params);
        return this.successRender().add("jokes",jokes);
    }
}
