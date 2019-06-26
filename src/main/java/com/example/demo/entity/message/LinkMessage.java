package com.example.demo.entity.message;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 链接消息
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class LinkMessage extends BaseMessage {
	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;



	@Override
	public String getMsgType() {
		return MessageType.LINK_MESSAGE.toString();
	}

}
