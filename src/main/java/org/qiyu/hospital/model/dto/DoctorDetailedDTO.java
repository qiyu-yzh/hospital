package org.qiyu.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDetailedDTO {
    private DoctorDTO doctor;
    private TypeDTO type;
}
