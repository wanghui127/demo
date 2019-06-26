package com.example.demo.entity.output;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 文本回复消息
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class OutTextMessage extends BaseOutMessage {
	// 文本消息
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String getMsgType() {
		return MessageType.RESP_MESSAGE_TYPE_TEXT.toString();
	}
}
