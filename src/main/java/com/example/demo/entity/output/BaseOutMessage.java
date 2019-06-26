package com.example.demo.entity.output;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 回复消息的公共字段类
 * 
 * @author Administrator
 *
 */
@Component
@Data
public abstract class BaseOutMessage {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;

	// 获取消息类型
	public abstract String getMsgType();
	


}
