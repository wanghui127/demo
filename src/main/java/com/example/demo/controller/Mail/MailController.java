package com.example.demo.controller.Mail;

import com.example.demo.entity.mail.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    private static Logger LOG= LoggerFactory.getLogger(MailController.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;
    /*
     * 发送普通邮件
     */
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getSender());
        message.setTo(mail.getReceiver());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        mailSender.send(message);
        LOG.info("发送成功!");
        return "发送成功！";
    }


    /*
     *  发送附件
     */
    @PostMapping("/sendAttachments")
    public String sendAttachmentsMail(@RequestBody Mail mail) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mail.getSender());
        helper.setTo(mail.getReceiver());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getText());
        FileSystemResource file = new FileSystemResource(new File("F:/gk4.jpg"));
        //发送附件
        helper.addAttachment("附件.jpg", file);
        mailSender.send(mimeMessage);
        return "发送成功!";

    }

    /*
     * 发送文件
     */
    @PostMapping("/sendInlineMail")
    public String sendInlineMail(@RequestBody Mail mail) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mail.getSender());
        helper.setTo(mail.getReceiver());
        helper.setSubject(mail.getSubject());
        //这里的text 是html
        helper.setText("<html><body>这是有图片的邮件：<img src=\'cid:neo006\' ></body></html>", true);
        FileSystemResource file = new FileSystemResource(new File("F:/gk4.jpg"));
        //发送正文中有静态资源（图片）的邮件(neo006与<img src='cid:neo006')
        helper.addInline("neo006", file);

        mailSender.send(mimeMessage);
        return "发送成功!";
    }


    /*
     * 发送模板
     */
    @PostMapping("/sendTemplateMail")
    public String sendTemplateMail(@RequestBody Mail mail) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mail.getSender());
        helper.setTo(mail.getReceiver());
        helper.setSubject(mail.getSubject());

        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "1");
        context.setVariable("name", "wh");
        String emailContent = templateEngine.process("email/emailTemplate", context);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
        return "发送成功!";
    }

    /*
     * 发送html内容
     */
    @PostMapping("/sendHtmlMail")
    public String sendHtmlMail(@RequestBody Mail mail) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail.getSender());
            helper.setTo(mail.getReceiver());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText(), true);

            mailSender.send(message);
            LOG.info("html邮件发送成功");
        } catch (MessagingException e) {
            LOG.error("发送html邮件时发生异常！", e);
        }
        return "发送成功!";
    }

}
