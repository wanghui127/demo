package com.example.demo.entity.output;

import java.util.List;
import com.example.demo.utils.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 提供了获取多条图文消息信息
 * 
 * @author Administrator
 *
 */
@Component
@Data
public class NewsOutputMessage extends BaseOutMessage {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Articles> Articles;



	@Override
	public String getMsgType() {
		return MessageType.RESP_MESSAGE_TYPE_NEWS.toString();
	}
}
