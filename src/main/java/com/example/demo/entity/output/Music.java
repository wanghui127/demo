package com.example.demo.entity.output;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 回复音乐消息中的音乐对象
 * @author Administrator
 *
 */
@Component
@Data
public class Music {
	// 音乐标题
	private String Title;
	// 音乐描述
	private String Description;
	// 音乐链接
	private String MusicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HQMusicUrl;
	// 缩略图的媒体id，通过上传多媒体文件得到的id
	private String ThumbMediaId;



}
