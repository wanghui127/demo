package com.example.demo.entity.message;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 图片消息
 * @author Administrator
 *
 */
@Component
@Data
public class ImageMessage extends BaseMessage{
	// 图片链接
    private String PicUrl;
    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId;



	@Override
	public String getMsgType() {
		return MessageType.IMAGE_MESSAGE.toString();
	}

}
