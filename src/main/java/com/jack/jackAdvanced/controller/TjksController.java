package com.jack.jackAdvanced.controller;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.jack.jackAdvanced.domain.entity.TjksEntity;
import com.jack.jackAdvanced.result.Result;
import com.jack.jackAdvanced.service.TjksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  考生分表接口
 */
@RestController
@RequestMapping("/tjks")
public class TjksController {

    @Autowired
    private TjksService tjksService;

    /**
     * 查询体检考生数据（是否会查询所有库）
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        List<TjksEntity> list = tjksService.listTjks();
        return Result.success(JSONUtil.toJsonStr(list));
    }

    /**
     * 保存走主库
     * @return
     */
    @GetMapping("/save")
    public Result save(){
        for (TjksEntity tjksEntity : getData()) {
            tjksService.save(tjksEntity);
        }
        return Result.success("save success....");
    }


    /**
     * 模拟插入数据
     */
    private List<TjksEntity> getData() {
        List<TjksEntity> userList = Lists.newArrayList();
        userList.add(new TjksEntity("小小11", "女", 3));
        userList.add(new TjksEntity("爸爸22", "男", 30));
        userList.add(new TjksEntity("妈妈33", "女", 28));
        userList.add(new TjksEntity("爷爷44", "男", 64));
        userList.add(new TjksEntity("奶奶55", "女", 62));
        return userList;
    }

}
