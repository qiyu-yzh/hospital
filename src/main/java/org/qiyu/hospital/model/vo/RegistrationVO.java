package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationVO {
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @Min(value = 0, message = "年龄不能小于0")
    @Max(value = 120, message = "年龄不能大于120")
    private Short age;

    @Min(value = 0, message = "性别不能小于0")
    private Short gender;

    @NotBlank(message = "时间不能为空")
    private String time;

    @Pattern(regexp = "^[0-9A-Fa-f-]+$", message = "医生uuid格式错误")
    private String doctor;

    @Pattern(regexp = "^[0-9A-Fa-f-]+$", message = "类型uuid格式错误")
    private String type;

}
