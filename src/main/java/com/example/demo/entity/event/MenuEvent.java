package com.example.demo.entity.event;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单事件
 * @author Administrator
 *
 */
@Component
@Data
public class MenuEvent extends BaseEvent {
	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;
    


}
