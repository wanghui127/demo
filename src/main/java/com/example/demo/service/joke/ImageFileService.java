package com.example.demo.service.joke;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.demo.entity.joke.ImageFile;
import com.example.demo.mapper.joke.ImageFileMapper;
public interface ImageFileService{

   int deleteByPrimaryKey(String id);

    
   int insert(ImageFile record);

    
    int insertSelective(ImageFile record);

    
    ImageFile selectByPrimaryKey(String id);

    
    int updateByPrimaryKeySelective(ImageFile record);

    
    int updateByPrimaryKey(ImageFile record);

}
