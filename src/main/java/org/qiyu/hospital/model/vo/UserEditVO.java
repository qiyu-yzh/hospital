package org.qiyu.hospital.model.vo;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditVO {

    @Pattern(regexp = "^[0-9a-f-]+$", message = "用户ID格式不正确")
    private String uuid;
    @Pattern(regexp = "^[0-9A-Za-z-_]{4,50}", message = "用户名格式不正确")
    private String userName;

    @Pattern(regexp = "^1[3-9][0-9]{9}", message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = "^(|\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)$", message = "邮箱格式有误")
    private String email;

}
