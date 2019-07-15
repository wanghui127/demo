package com.example.demo.entity.mail;

import lombok.Data;

@Data
public class Mail {

    /** 发送者*/
    private String sender;

    /** 接受者  */
    private String receiver;

    /** 主题 */
    private String subject;

    /** 发送 消息*/
    private String text;
}
