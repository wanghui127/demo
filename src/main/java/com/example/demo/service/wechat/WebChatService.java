package com.example.demo.service.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.output.Articles;
import com.example.demo.entity.output.NewsOutputMessage;
import com.example.demo.entity.output.OutTextMessage;
import com.example.demo.utils.MessageType;
import com.example.demo.utils.ReplyMessageUtil;
import com.example.demo.utils.XmlUtil;

/**
 * 处理接收信息和回复消息的服务类接口
 *
 * @author Administrator
 *
 */
public class WebChatService {
    // 处理微信发来的请求 map 消息业务处理分发
    public static String parseMessage(Map<String, String> map) {
        String respXml = null;
        try {
            // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            // 取得消息类型
            String MsgType = map.get("MsgType");
            //取得消息内容
            String msgContent = map.get("Content");
            // 发现直接把要返回的信息直接封装成replyMap集合，然后转换成 xml文件，是不是实体类可以不用了
            Map<String, String> replyMap = new HashMap<String, String>();
            replyMap.put("ToUserName", fromUserName);
            replyMap.put("FromUserName", toUserName);
            replyMap.put("CreateTime", String.valueOf(new Date().getTime()));
            if (MsgType.equals(MessageType.TEXT_MESSAGE)) {
                // 封装文本返回消息
                OutTextMessage textMessage = new OutTextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setContent("您发送的是:"+msgContent);
                textMessage.getMsgType();
                //respXml = ReplyMessageUtil.sendTextMessage(textMessage);

                // 用map集合封装
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", map.get("jokeContent"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.IMAGE_MESSAGE)) {
                // 这里回复图片 或者图文消息 以图文消息为例
                NewsOutputMessage message = new NewsOutputMessage();
                message.setToUserName(fromUserName);
                message.setFromUserName(toUserName);
                message.setCreateTime(new Date().getTime());
                message.getMsgType();
                //取得消息内容
                String picUrl = map.get("PicUrl");
                Articles article = new Articles();
                article.setDescription("图文消息 "); // 图文消息的描述
                article.setPicUrl(picUrl+"?size=458x240"); // 图文消息图片地址
                article.setTitle("活动推送 "); // 图文消息标题
                article.setUrl("http://3hqqir.natappfree.cc/demo/login"); // 图文 url 链接
                List<Articles> list = new ArrayList<Articles>();
                list.add(article);// 这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个
                // Articles！

                message.setArticleCount(list.size());
                message.setArticles(list);
                respXml = ReplyMessageUtil.sendImageTextMessage(message);
            } else if (MsgType.equals(MessageType.VOICE_MESSAGE)) {
                // 以下方式根据需要来操作
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是语音消息");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.VIDEO_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是视频消息");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.SHORTVIDEO_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是小视频消息");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.POSOTION_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是地理位置消息");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.LINK_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是链接消息");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }else if (MsgType.equals(MessageType.EVENT_TYPE_SUBSCRIBE)){
                // 用map集合封装
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是：");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    // 事件消息业务分发
    public static String parseEvent(Map<String, String> map) {
        String respXml = null;
        try {
            // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            // 取得消息类型
            String MsgType = map.get("MsgType");
            //获取事件类型
            String eventType = map.get("Event");

            // 发现直接把要返回的信息直接封装成replyMap集合，然后转换成 xml文件，是不是实体类可以不用了
            Map<String, String> replyMap = new HashMap<String, String>();
            replyMap.put("ToUserName", fromUserName);
            replyMap.put("FromUserName", toUserName);
            replyMap.put("CreateTime", String.valueOf(new Date().getTime()));
            if (eventType.equals(MessageType.EVENT_TYPE_SUBSCRIBE)) {// 关注
                // 用map集合封装
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "哎呦不错哦，挺有眼光的！回复关键字“笑话”，获取爆笑笑话！");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }
            if (eventType.equals(MessageType.EVENT_TYPE_UNSUBSCRIBE)) {// 取消关注
                // 用map集合封装
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "客观，下次再来玩啊！");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }
            if (eventType.equals(MessageType.EVENT_TYPE_SCAN)) {// 用户已关注时的扫描带参数二维码

            }
            if (eventType.equals(MessageType.EVENT_TYPE_LOCATION)) {// 上报地理位置

            }
            if (eventType.equals(MessageType.EVENT_TYPE_CLICK)) {// 自定义菜单

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
}
