package org.qiyu.hospital.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDO {
    private String doctorUuid;
    private String user;
    private boolean expert;
    private String type;
}