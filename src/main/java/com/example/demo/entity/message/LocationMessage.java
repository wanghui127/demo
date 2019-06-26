package com.example.demo.entity.message;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 地理位置消息
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class LocationMessage extends BaseMessage {
	// 地理位置维度
	private String Location_X;
	// 地理位置经度
	private String Location_Y;
	// 地图缩放大小
	private Long Scale;
	// 地理位置信息
	private String Label;



	@Override
	public String getMsgType() {
		return MessageType.POSOTION_MESSAGE.toString();
	}

}
