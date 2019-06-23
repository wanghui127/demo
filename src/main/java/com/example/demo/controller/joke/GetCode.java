package com.example.demo.controller.joke;

import com.example.demo.utils.CodeUtil;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.JsonResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: demo
 * @description: 获取验证码与登陆页面
 * @author: Hui.Wang
 * @Time: $
 * @create: 2019-06-23 12:01
 **/
@Controller
public class GetCode  extends JsonResultUtil{


    /** 
    * @Description:  登陆页面
    * @Param:  
    * @return:  
    * @Author: Hui.Wang 
    * @Date: 2019/6/23 0023 
    * @Time: 13:54 
    */ 
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /** 
    * @Description:  获取验证码
    * @Param:
    * @return:  
    * @Author: Hui.Wang 
    * @Date: 2019/6/23 0023 
    * @Time: 12:07 
    */ 
    @RequestMapping(value = "getCode")
    @ResponseBody
    public void getCode(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /** 
    * @Description: 校验登陆信息
    * @Param:  
    * @return:  
    * @Author: Hui.Wang 
    * @Date: 2019/6/23 0023 
    * @Time: 13:35 
    */ 
    @RequestMapping(value = "checkCode",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkCode(@RequestBody Map<String,String> params, HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws ServletException, IOException {
        String phone = params.get("phone");
        String password = params.get("password");
        String code = params.get("vercode");
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
                session.setAttribute("user",code);
                return this.successRender().message("验证通过！");
            } else {
                return this.failRender().message("验证输入不正确，请重新输入");
            }
        } else {
            return this.failRender().message("验证失败！");
        }
    }
}
