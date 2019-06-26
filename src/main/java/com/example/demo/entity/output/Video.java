package com.example.demo.entity.output;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 视频model
 * @author Administrator
 *
 */
@Component
@Data
public class Video {
	// 媒体文件id
	private String MediaId;
	// 缩略图的媒体id
	private String ThumbMediaId;
	//视频消息的标题
	private String Title; 
	//视频消息的描述
	private String Description;

	
}
