package org.qiyu.hospital.model.vo;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsoleTypeAddVO {
    @Pattern(regexp = "^[0-9A-Za-z-_]{4,48}", message = "类型名称格式不正确")
    private String outpatientType;
    @Pattern(regexp = "^[0-9A-Za-z-_]{4,48}", message = "类型简介格式不正确")
    private String outpatientIntroduce;
}
