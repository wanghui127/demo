package com.example.demo.mapper.joke;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.joke.ImageFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ImageFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(ImageFile record);

    int insertSelective(ImageFile record);

    ImageFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ImageFile record);

    int updateByPrimaryKey(ImageFile record);

    //根据imageId查询多条记录
    List<ImageFile> selectByImageIdOrderByCreateTimeDesc(@Param("imageId")String imageId);

    //查询flag为o正常的图片数据count
    Integer countByFlag(@Param("flag")Integer flag);


    //分页查询所有数据
    List<ImageFile> selectPage(Map<String, String> params);
}