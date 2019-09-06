package com.example.demo.entity.joke;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ImageFile {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 图片地址
    */
    private String picSrc;

    /**
    * 标志 0正常 1删除
    */
    private Integer flag;

    /**
    * 图片表id，与image关联
    */
    private String imageId;
}