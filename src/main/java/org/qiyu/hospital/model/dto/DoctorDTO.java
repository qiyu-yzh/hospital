package org.qiyu.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private String doctorUuid;
    private String user;
    private String type;
    private String realName;
    private String college;
    private String dateBirth;
    private Boolean expert;
    private String attending;
    private String description;
}

