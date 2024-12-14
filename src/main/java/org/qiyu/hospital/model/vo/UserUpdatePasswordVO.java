package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordVO {

    @Pattern(regexp = "^[0-9a-f-]+$", message = "用户ID格式不正确")
    private String uuid;

    private String password;


}
