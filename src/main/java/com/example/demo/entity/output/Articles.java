package com.example.demo.entity.output;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 图文消息实体类对象
 * @author Administrator
 *
 */
@Component
@Data
public class Articles {
	private String Title;
	// 图文消息描述
	private String Description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
	private String PicUrl;
	// 点击图文消息跳转链接
	private String Url;


}
