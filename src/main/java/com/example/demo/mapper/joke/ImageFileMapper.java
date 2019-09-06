package com.example.demo.mapper.joke;

import com.example.demo.entity.joke.ImageFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageFile record);

    int insertSelective(ImageFile record);

    ImageFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageFile record);

    int updateByPrimaryKey(ImageFile record);
}