package com.example.demo.entity.message;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 视频消息
 * @author Administrator
 *
 */
@Component
@Data
public class VideoMessage extends BaseMessage {
	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;
	// 视频消息 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;



	@Override
	public String getMsgType() {
		return MessageType.VIDEO_MESSAGE.toString();
	}

}
