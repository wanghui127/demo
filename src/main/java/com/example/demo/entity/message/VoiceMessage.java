package com.example.demo.entity.message;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 语音消息
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class VoiceMessage extends BaseMessage {
	// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;
	// 语音格式，如amr，speex等
	private String Format;
	// 语音识别结果，使用UTF8编码
	private String Recognition;



	@Override
	public String getMsgType() {
		return MessageType.VOICE_MESSAGE.toString();
	}

}
