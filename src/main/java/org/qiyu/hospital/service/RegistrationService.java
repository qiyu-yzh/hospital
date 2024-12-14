package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.RegistrationDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.RegistrationVO;

import java.text.ParseException;
import java.util.List;

public interface RegistrationService {
    void addRegistration(UserDTO userDTO, RegistrationVO registrationVO) throws ParseException;

    List<RegistrationDTO> consoleGetRegistrationList();


    void deleteRegistration(String uuid);

    RegistrationDTO getRegistration(String uuid);

    List<RegistrationDTO> getRegistrationList(UserDTO userDTO);

    List<RegistrationDTO> getRegistrationsByDoctor(String doctorUuid);


}
