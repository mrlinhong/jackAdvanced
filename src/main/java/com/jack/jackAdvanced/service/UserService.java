package com.jack.jackAdvanced.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.jackAdvanced.domain.entity.User;
import com.jack.jackAdvanced.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    public List<User> listUser(){
        return userMapper.listUser();
    }

    public Page<User> pageListUser(Page page){
        return userMapper.pageListUser(page);
    }

}
