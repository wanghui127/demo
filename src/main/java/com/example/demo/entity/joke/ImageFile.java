package com.example.demo.entity.joke;

import java.util.Date;
import lombok.Data;

@Data
public class ImageFile {
    /**
     * 主键
     */
    private String id;

    /**
     * 图片地址
     */
    private String picSrc;

    /**
     * 标志 0正常 1删除
     */
    private Integer flag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 图片表id，与image关联
     */
    private String imageId;
}