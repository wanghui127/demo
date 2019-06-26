package com.example.demo.entity.message;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 文本消息
 * @author Administrator
 *
 */
@Component
@Data
public class TextMessage extends BaseMessage {
	//文本消息内容
	private String Content;



	@Override
	public String getMsgType() {
		return MessageType.TEXT_MESSAGE.toString();
	}

}
