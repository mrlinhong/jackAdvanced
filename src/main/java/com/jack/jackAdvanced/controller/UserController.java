package com.jack.jackAdvanced.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.jackAdvanced.domain.entity.User;
import com.jack.jackAdvanced.domain.vo.LoginParam;
import com.jack.jackAdvanced.exception.JackException;
import com.jack.jackAdvanced.result.Result;
import com.jack.jackAdvanced.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.jack.jackAdvanced.result.CodeMsg.BIND_ERROR;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result list(){
        List<User> list = userService.listUser();
        return Result.success(JSONUtil.toJsonStr(list));
    }

    @GetMapping("/pageList")
    public Result pageList(Page page){
        return Result.success(userService.pageListUser(page));
    }

    @GetMapping("/exception")
    public Result exception(Page page){
        if (true){
            throw new JackException(BIND_ERROR);
        }
        return Result.success(userService.pageListUser(page));
    }

    @GetMapping("/validParam")
    public Result validParam(@Valid LoginParam loginParam){
        System.out.println(JSONUtil.toJsonStr(loginParam));
        return Result.success("通过参数校验");
    }

}
