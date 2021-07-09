package com.jack.jackAdvanced.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@TableName("tb_tjks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TjksEntity {

    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private String address;
    private Integer age;

}
