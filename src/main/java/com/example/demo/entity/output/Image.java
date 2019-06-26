package com.example.demo.entity.output;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 图片回复消息
 * @author Administrator
 *
 */
@Component
@Data
public class Image{
	//通过上传多媒体文件，得到的id
	private String MediaId;
	

}
