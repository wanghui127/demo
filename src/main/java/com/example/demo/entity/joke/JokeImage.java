package com.example.demo.entity.joke;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
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
     * 删除标志 0 正常   1删除
     */
    private Integer flag;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 绑定发布人
     */
    private String userId;
}