package com.example.demo.service.joke;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.demo.entity.joke.ImageFile;
import com.example.demo.mapper.joke.ImageFileMapper;
public interface ImageFileService{

   int deleteByPrimaryKey(Integer id);

    
   int insert(ImageFile record);

    
    int insertSelective(ImageFile record);

    
    ImageFile selectByPrimaryKey(Integer id);

    
    int updateByPrimaryKeySelective(ImageFile record);

    
    int updateByPrimaryKey(ImageFile record);

}
