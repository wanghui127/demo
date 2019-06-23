package com.example.demo.service.joke.impl;

import com.example.demo.entity.joke.Joke;
import com.example.demo.mapper.joke.JokeMapper;
import com.example.demo.service.joke.JokeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class JokeServiceImpl implements JokeService {
    @Resource
    JokeMapper jokeMapper;

    @Override
    public int selectCount(Map<String, String> params) {
        return jokeMapper.selectCount(params);
    }

    @Override
    public List<Joke> selectPage(Map<String, String> params) {
        return jokeMapper.selectPage(params);
    }
}
