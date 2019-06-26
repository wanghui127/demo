package com.example.demo.entity.output;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 回复音乐消息
 * @author Administrator
 *
 */
@Component
@Data
public class MusicOutputMessage extends BaseOutMessage {
	private Music Music;



	@Override
	public String getMsgType() {
		return MessageType.RESP_MESSAGE_TYPE_MUSIC.toString();
	}
}
