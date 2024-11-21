package org.qiyu.hospital.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDO {
    private String registrationUuid;
    private String user;
    private String doctor;
    private String type;
    private double price;
    private Timestamp time;
    private String realName;
    private int age;
    private int gender;
    private Timestamp createdAt;
    private boolean isFinal;
}