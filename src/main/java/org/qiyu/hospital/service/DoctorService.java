package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.DoctorDTO;
import org.qiyu.hospital.model.vo.DoctorAddVO;

import java.text.ParseException;
import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getDoctorList();

    void addDoctor(DoctorAddVO doctorAddVO) throws ParseException;
}
