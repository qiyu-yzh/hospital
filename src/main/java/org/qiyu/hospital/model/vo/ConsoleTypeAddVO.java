package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsoleTypeAddVO {
    @NotBlank( message = "类型名称不为空")
    private String outpatientType;
    @NotBlank( message = "类型简介为空")
    private String description;
}
