package com.example.demo.mapper.joke;

import com.example.demo.entity.joke.JokeImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(JokeImage record);

    int insertSelective(JokeImage record);

    JokeImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JokeImage record);

    int updateByPrimaryKey(JokeImage record);
}