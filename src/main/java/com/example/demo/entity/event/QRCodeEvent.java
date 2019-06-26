package com.example.demo.entity.event;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 扫描带参数二维码事件
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class QRCodeEvent extends BaseEvent {
	// 事件KEY值
	private String EventKey;
	// 用于换取二维码图片
	private String Ticket;



}
