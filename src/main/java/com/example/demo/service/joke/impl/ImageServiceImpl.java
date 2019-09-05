package com.example.demo.service.joke.impl;

import com.example.demo.entity.joke.JokeImage;
import com.example.demo.mapper.joke.ImageMapper;
import com.example.demo.service.joke.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageMapper imageMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(String id) {
        return imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(JokeImage record) {
        return imageMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(JokeImage record) {
        return imageMapper.insertSelective(record);
    }

    @Override
    public JokeImage selectByPrimaryKey(String id) {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(JokeImage record) {
        return imageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(JokeImage record) {
        return imageMapper.updateByPrimaryKey(record);
    }
}
