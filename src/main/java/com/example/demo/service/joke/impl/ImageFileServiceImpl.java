package com.example.demo.service.joke.impl;

import com.example.demo.entity.joke.ImageFile;
import com.example.demo.mapper.joke.ImageFileMapper;
import com.example.demo.service.joke.ImageFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ImageFileServiceImpl implements ImageFileService {

    @Resource
    ImageFileMapper imageFileMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imageFileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ImageFile record) {
        return imageFileMapper.insert(record);
    }

    @Override
    public int insertSelective(ImageFile record) {
        return imageFileMapper.insertSelective(record);
    }

    @Override
    public ImageFile selectByPrimaryKey(Integer id) {
        return imageFileMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImageFile record) {
        return imageFileMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImageFile record) {
        return imageFileMapper.updateByPrimaryKey(record);
    }
}
