package com.example.demo.service.joke;

import com.example.demo.entity.joke.Joke;

import java.util.List;
import java.util.Map;

public interface JokeService {

    //分页查询所有数据
    List<Joke> selectPage(Map<String, String> params);
}
