package com.example.demo.controller.joke;

import com.example.demo.service.wechat.WebChatService;
import com.example.demo.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
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


    /**
     * 微信开发者测试申请
     */
    @RequestMapping("/wechatTest")
    @ResponseBody
    public void wechatTest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //获取Get请求携带参数
        String content=request.getQueryString();
        System.out.println(content);
        if(content.startsWith("signature")){
            //检查消息是否来自微信服务器
            String echostr=CheckSignature(content);

            //返回echostr给微信服务器
            OutputStream os=response.getOutputStream();
            os.write(URLEncoder.encode(echostr,"UTF-8").getBytes());
            os.flush();
            os.close();
        }
    }

    /**
     * 接收消息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wechatTest",method = RequestMethod.POST)
    @ResponseBody
    public void wechatTestPost(HttpServletRequest request, HttpServletResponse response)throws Exception{
        // TODO 消息的接收、处理、响应
        //消息来源可靠性验证
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");       // 随机数
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        /*if (!WebChatUtil.checkSignature(signature, timestamp, nonce)) {
            //消息不可靠，直接返回
            response.getWriter().write("");
            return;
        }*/
        //用户每次向公众号发送消息、或者产生自定义菜单点击事件时，响应URL将得到推送
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");
            //调用parseXml方法解析请求消息
            Map<String, String> map = MessageType.parseXml(request, response);
            String MsgType = map.get("MsgType");
            String xml = null;//处理输入消息，返回结果的xml
            if(MessageType.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)){
                xml = WebChatService.parseEvent(map);
            }else{
                xml = WebChatService.parseMessage(map);
            }
            //返回封装的xml
            //System.out.println(xml);
            response.getWriter().write(xml);
        } catch (Exception ex) {
            response.getWriter().write("");
        }
    }

    public static String CheckSignature(String str){
        String[] content=str.split("&");
        String signature=content[0].split("=")[1];
        String timestamp=content[2].split("=")[1];
        String nonce=content[3].split("=")[1];
        //第一步中填写的token一致
        String token="wxsxw";

        ArrayList<String> list=new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add(token);

        //字典序排序
        Collections.sort(list);
        //SHA1加密
        String checksignature= SHA1.encode(list.get(0)+list.get(1)+list.get(2));
        System.out.println(signature);
        System.out.println(checksignature);

        if(checksignature.equals(signature)){
            return content[1].split("=")[1];
        }
        return null;
    }



}
