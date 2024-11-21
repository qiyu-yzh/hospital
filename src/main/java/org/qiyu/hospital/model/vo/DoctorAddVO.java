package org.qiyu.hospital.model.vo;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorAddVO {

    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @NotBlank(message = "毕业院校不能为空")
    private String college;

    @NotBlank(message = "出生日期不能为空")
    private String dateBirth;

    private Boolean expert;

    @NotBlank(message = "主治不能为空")
    private String attending;

    @NotBlank(message = "简介不能为空")
    private String description;

    @Pattern(regexp = "^[0-9A-Fa-f-]+$", message = "用户uuid格式错误")
    private String user;

    @Pattern(regexp = "^[0-9A-Fa-f-]+$", message = "类型uuid格式错误")
    private String type;
}
