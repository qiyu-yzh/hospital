package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserLoginVO {
    @Pattern(regexp = "^[0-9A-Za-z-_]{4,50}", message = "用户名格式不正确")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
}

