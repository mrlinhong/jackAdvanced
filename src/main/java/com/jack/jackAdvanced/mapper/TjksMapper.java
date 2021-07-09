package com.jack.jackAdvanced.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.jackAdvanced.domain.entity.TjksEntity;

import java.util.List;

public interface TjksMapper extends BaseMapper<TjksEntity> {

    List<TjksEntity> listTjks();

    void save(TjksEntity tjksEntity);
}
