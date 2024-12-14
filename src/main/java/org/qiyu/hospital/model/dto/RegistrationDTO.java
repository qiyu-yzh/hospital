package org.qiyu.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String registrationUuid;
    private String user;
    private String doctor;
    private String type;
    private Double price;
    private Timestamp time;
    private String realName;
    private Integer age;
    private Integer gender;
    private Timestamp createdAt;
    private Boolean isFinal;
}
