package com.example.demo.entity.joke;

import java.util.Date;
import lombok.Data;

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 绑定发布人
     */
    private String userId;
}