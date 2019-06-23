package com.example.demo.service.joke;

import com.example.demo.entity.joke.Joke;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JokeService {
    //分页查询所有数据
    int selectCount(Map<String, String> params);

    //分页查询所有数据
    List<Joke> selectPage(Map<String, String> params);

    //修改内容
    void updateById(int id,String content);

    //删除数据
    void deleteById(int id);

    //插入数据
    void insertJoke(Joke joke);
}
