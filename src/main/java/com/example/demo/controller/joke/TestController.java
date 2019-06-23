package com.example.demo.controller.joke;

import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import com.example.demo.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: demo
 * @description: 测试Controller
 * @author: Hui.Wang
 * @Time: $
 * @create: 2019-06-23 21:50
 **/
@RequestMapping("/test")
@Controller
public class TestController extends JsonResultUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    RedisUtil redisUtil;

    /** 
    * @Description:  redis连接测试
    * @Param:  
    * @return:  
    * @Author: Hui.Wang 
    * @Date: 2019/6/23 0023 
    * @Time: 21:56 
    */ 
    @RequestMapping("/redisTest")
    @ResponseBody
    public JsonResult redisTest(){
        try {
            redisUtil.set("set","test");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return this.successRender().message(redisUtil.get("set").toString());
    }
}
