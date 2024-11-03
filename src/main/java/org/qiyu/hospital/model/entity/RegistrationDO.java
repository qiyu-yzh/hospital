package org.qiyu.hospital.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDO {
    private String registrationUuid;
    private String user;
    private double price;
    private Timestamp time;
    private String doctor;
    private String type;
}

