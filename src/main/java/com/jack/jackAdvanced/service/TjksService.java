package com.jack.jackAdvanced.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.jackAdvanced.domain.entity.TjksEntity;
import com.jack.jackAdvanced.domain.entity.User;
import com.jack.jackAdvanced.mapper.TjksMapper;
import com.jack.jackAdvanced.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TjksService extends ServiceImpl<TjksMapper, TjksEntity>  {

    @Autowired
    private TjksMapper tjksMapper;

    public List<TjksEntity> listTjks() {
        return tjksMapper.listTjks();
    }

    public void saveTjks(TjksEntity tjksEntity){
        tjksMapper.save(tjksEntity);
    }

}
