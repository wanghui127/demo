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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

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

    /**
     * redis测试(目前使用的是ip地址，后期根据手机号来作为key,ip地址作为他申请次数，超过5次，就不给申请)
     * @param
     * @param
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/med/redisTest")
    @ResponseBody
    public JsonResult redisTest(HttpServletRequest request){
        //6位随机数字，模拟短信验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        // 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
        //可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
        //答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
        //获取请求真实ip
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")){
            ip = "127.0.0.1";
        }
        //获取请求端口号
        int port = request.getRemotePort();
        if (!redisUtil.hasKey(ip)){
            redisUtil.set(ip,verifyCode);
            redisUtil.expire(ip,60);
            return this.successRender().message("验证码为:"+verifyCode);
        }else {
            long expire =  redisUtil.getExpire(ip);
            return this.failRender().message("验证码"+expire+"秒后过期！");
        }
    }
}
