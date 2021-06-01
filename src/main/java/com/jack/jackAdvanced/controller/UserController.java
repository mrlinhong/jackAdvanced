package com.jack.jackAdvanced.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.jackAdvanced.domain.entity.User;
import com.jack.jackAdvanced.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String list(){
        List<User> list = userService.listUser();
        System.out.println(list.size());
        return JSONUtil.toJsonStr(list);
    }

    @GetMapping("/pageList")
    public Page<User> pageList(Page page){
        return userService.pageListUser(page);
    }

    @GetMapping("/save")
    public String save(){
        User u = User.builder()
                .openid("xxxxid")
                .address("nanjing")
                .username("jack lin")
                .deleted(1)
                .build();
        userService.save(u);
        return "jack";
    }
}
