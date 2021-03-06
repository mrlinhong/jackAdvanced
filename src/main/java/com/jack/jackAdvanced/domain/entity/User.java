package com.jack.jackAdvanced.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user")
@Builder
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String address;

    private String openid;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer deleted;

}
