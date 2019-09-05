package com.example.demo.entity.joke;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JokeImage {
    /**
    * 图片表id
    */
    private String id;

    /**
    * 图片的标题
    */
    private String title;

    /**
    * 图片地址
    */
    private String picSrc;

    /**
    * 删除标志 0 正常   1删除
    */
    private Integer flag;

    /**
    * 绑定发布人
    */
    private String userId;
}