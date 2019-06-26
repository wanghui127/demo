package com.example.demo.entity.output;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 回复视频消息
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class VideoOutPutMessage extends BaseOutMessage {
	private Video Video;



	@Override
	public String getMsgType() {
		return MessageType.RESP_MESSAGE_TYPE_VIDEO.toString();
	}
}
