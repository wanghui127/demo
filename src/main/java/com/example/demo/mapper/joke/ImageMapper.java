package com.example.demo.mapper.joke;
import com.example.demo.entity.joke.JokeImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(JokeImage record);

    int insertSelective(JokeImage record);

    JokeImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JokeImage record);

    int updateByPrimaryKey(JokeImage record);

    //分页查询所有数据
    int selectCount(Map<String, String> params);

    //分页查询所有数据
    List<JokeImage> selectPage(Map<String, String> params);

    //删除（修改状态）
    int updateFlagAndUpdateTimeById(@Param("updatedFlag")Integer updatedFlag,@Param("updatedUpdateTime")Date updatedUpdateTime,@Param("id")String id);


}