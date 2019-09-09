package com.example.demo.service.joke;

import com.example.demo.entity.joke.ImageFile;

import java.util.List;

public interface ImageFileService{

   int deleteByPrimaryKey(String id);

    
   int insert(ImageFile record);

    
    int insertSelective(ImageFile record);

    
    ImageFile selectByPrimaryKey(String id);

    
    int updateByPrimaryKeySelective(ImageFile record);

    
    int updateByPrimaryKey(ImageFile record);

    //根据imageId查询多条记录
    List<ImageFile> selectByImageIdOrderByCreateTimeDesc(String imageId);

}
