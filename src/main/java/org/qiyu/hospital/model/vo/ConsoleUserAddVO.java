package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsoleUserAddVO {
    @Pattern(regexp = "^[0-9A-Za-z-_]{4,50}", message = "用户名格式不正确")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Pattern(regexp = "^1[3-9][0-9]{9}", message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = "^(|\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)$", message = "邮箱格式有误")
    private String email;
    @Pattern(regexp = "^[0-9a-f-]+$", message = "角色格式不正确")
    private String role;
}
