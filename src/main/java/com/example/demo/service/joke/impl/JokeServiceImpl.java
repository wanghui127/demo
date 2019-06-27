package com.example.demo.service.joke.impl;

import com.example.demo.entity.joke.Joke;
import com.example.demo.mapper.joke.JokeMapper;
import com.example.demo.service.joke.JokeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void updateById(int id, String content) {
        jokeMapper.updateById(id,content);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        jokeMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void insertJoke(Joke joke) {
         jokeMapper.insertJoke(joke);
    }

    @Override
    public Joke getOneRand() {
        return jokeMapper.getOneRand();
    }
}
