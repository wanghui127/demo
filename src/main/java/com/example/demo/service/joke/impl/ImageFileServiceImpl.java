package com.example.demo.service.joke.impl;

import com.example.demo.entity.joke.ImageFile;
import com.example.demo.mapper.joke.ImageFileMapper;
import com.example.demo.service.joke.ImageFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ImageFileServiceImpl implements ImageFileService {

    @Resource
    ImageFileMapper imageFileMapper;


    @Override
    @Transactional
    public int insert(ImageFile record) {
        return imageFileMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(ImageFile record) {
        return imageFileMapper.insertSelective(record);
    }


    @Override
    @Transactional
    public int updateByPrimaryKeySelective(ImageFile record) {
        return imageFileMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(ImageFile record) {
        return imageFileMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(String id) {
        return imageFileMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public ImageFile selectByPrimaryKey(String id) {
        return imageFileMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ImageFile> selectByImageIdOrderByCreateTimeDesc(String imageId) {
        return imageFileMapper.selectByImageIdOrderByCreateTimeDesc(imageId);
    }

    @Override
    public Integer countByFlag(Integer flag) {
        return imageFileMapper.countByFlag(flag);
    }

    @Override
    public List<ImageFile> selectPage(Map<String, String> params) {
        return imageFileMapper.selectPage(params);
    }
}
