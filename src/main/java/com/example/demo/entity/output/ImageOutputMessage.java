package com.example.demo.entity.output;

import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 图片输出内容
 * @author Administrator
 *
 */
@Component
@Data
public class ImageOutputMessage extends BaseOutMessage{
	private Image Image;

	@Override
	public String getMsgType() {
		return MessageType.RESP_MESSAGE_TYPE_IMAGE.toString();
	}
}
