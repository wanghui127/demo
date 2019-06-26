package com.example.demo.entity.wechat;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 类名: Token </br>
 * 描述:  凭证  </br>
 * 开发人员： souvc </br>
 * 创建时间：  2015-11-27 </br>
 * 发布版本：V1.0  </br>
 */
@Component
@Data
public class Token {
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;

}