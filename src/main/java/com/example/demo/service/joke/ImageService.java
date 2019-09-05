package com.example.demo.service.joke;

import com.example.demo.entity.joke.JokeImage;
public interface ImageService{

     int deleteByPrimaryKey(String id);

    
     int insert(JokeImage record);

    
     int insertSelective(JokeImage record);

    
     JokeImage selectByPrimaryKey(String id);

    
     int updateByPrimaryKeySelective(JokeImage record);

    
     int updateByPrimaryKey(JokeImage record);

}
