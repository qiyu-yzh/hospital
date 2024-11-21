package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsoleTypeEditVO {
    @Pattern(regexp = "^[0-9a-f-]+$", message = "类型ID格式不正确")
    private String typeUuid;
    @NotBlank(message = "门诊类型不能为空")
    private String outpatientType;
    @NotBlank(message = "门诊介绍不能为空")
    private String description;
}