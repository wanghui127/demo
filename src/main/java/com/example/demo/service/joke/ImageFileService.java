package com.example.demo.service.joke;

import com.example.demo.entity.joke.ImageFile;

import java.util.List;
import java.util.Map;

public interface ImageFileService{

   int deleteByPrimaryKey(String id);

    
   int insert(ImageFile record);

    
    int insertSelective(ImageFile record);

    
    ImageFile selectByPrimaryKey(String id);

    
    int updateByPrimaryKeySelective(ImageFile record);

    
    int updateByPrimaryKey(ImageFile record);

    //根据imageId查询多条记录
    List<ImageFile> selectByImageIdOrderByCreateTimeDesc(String imageId);

    //查询flag为o正常的图片数据count
    Integer countByFlag(Integer flag);

    //分页查询所有数据
    List<ImageFile> selectPage(Map<String, String> params);

}
