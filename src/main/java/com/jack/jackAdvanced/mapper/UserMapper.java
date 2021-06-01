package com.jack.jackAdvanced.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.jackAdvanced.domain.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> listUser();

    Page<User> pageListUser(Page page);
}
