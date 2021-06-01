package com.jack.jackAdvanced.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LoginParam {

    @NotNull(message = "手机号不能为空")
    private String mobile;
    @NotNull(message="密码不能为空")
    @Length(min = 7, message = "密码长度需要在7个字以内")
    private String password;
}
