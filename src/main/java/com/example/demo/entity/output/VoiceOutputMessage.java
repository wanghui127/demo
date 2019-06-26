package com.example.demo.entity.output;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 语音回复消息
 * @author Administrator
 *
 */
@Component
@Data
public class VoiceOutputMessage extends BaseOutMessage{
    private Voice voice;
    


	@Override
	public String getMsgType() {
		return MessageType.RESP_MESSAGE_TYPE_VOICE.toString();
	}
}
