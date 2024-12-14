package org.qiyu.hospital.service.logic;

import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.mapper.DoctorMapper;
import org.qiyu.hospital.mapper.RegistrationMapper;
import org.qiyu.hospital.model.dto.DoctorDTO;
import org.qiyu.hospital.model.entity.DoctorDO;
import org.qiyu.hospital.model.entity.RegistrationDO;
import org.qiyu.hospital.model.vo.DoctorAddVO;
import org.qiyu.hospital.model.vo.DoctorEditVO;
import org.qiyu.hospital.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorLogic implements DoctorService {
    private static final Logger log = LoggerFactory.getLogger(DoctorLogic.class);

    private final DoctorMapper doctorMapper;
    private final RegistrationMapper registrationMapper;


    @Override
    public List<DoctorDTO> getDoctorList() {
        List<DoctorDO> doctorList = doctorMapper.getDoctorList();
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for (DoctorDO doctorDO : doctorList) {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctorDO, doctorDTO);
            doctorDTOList.add(doctorDTO);
        }
        return doctorDTOList;
    }

    @Override
    public void addDoctor(DoctorAddVO doctorAddVO) throws ParseException {
        int doctorExist = doctorMapper.checkDoctorExist(doctorAddVO.getRealName());
        if (doctorExist != 0) {
            throw new BusinessException("医生已存在", ErrorCode.OPERATION_DENIED);
        }
        DoctorDO newDoctor = new DoctorDO();
        BeanUtils.copyProperties(doctorAddVO, newDoctor);
        newDoctor
                .setDoctorUuid(UuidUtil.generateStringUuid())
                .setDateBirth(String.valueOf(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(doctorAddVO.getDateBirth()).getTime())));
        doctorMapper.addDoctor(newDoctor);
    }

    @Override
    public DoctorDTO getDoctor(String doctorUuid) {
        DoctorDO doctorDO = doctorMapper.getDoctorUuid(doctorUuid);
        if (doctorDO == null) {
            throw new BusinessException("医生不存在", ErrorCode.OPERATION_DENIED);
        }
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctorDO, doctorDTO);
        return doctorDTO;
    }

    @Override
    public void deleteDoctor(String doctorUuid) {
        DoctorDO doctorDO = doctorMapper.getDoctorUuid(doctorUuid);
        if (doctorDO == null) {
            throw new BusinessException("医生不存在", ErrorCode.OPERATION_DENIED);
        }
        doctorMapper.deleteDoctor(doctorUuid);
    }

    @Override
    public void updateDoctor(DoctorEditVO doctorEditVO) {
        DoctorDO doctorDO = doctorMapper.getDoctorUuid(doctorEditVO.getDoctorUuid());
        if (doctorDO == null) {
            throw new BusinessException("医生不存在", ErrorCode.OPERATION_DENIED);
        }
        DoctorDO newDoctor = new DoctorDO();
        BeanUtils.copyProperties(doctorEditVO, newDoctor);
        doctorMapper.updateDoctor(newDoctor);
    }

    @Override
    public void houseCalls(String userUuid, String realName) {
        RegistrationDO getLastRegistration = registrationMapper.getLastRegistration(userUuid, realName);
        if (getLastRegistration == null) {
            throw new BusinessException("挂号不存在", ErrorCode.OPERATION_DENIED);
        }
        doctorMapper.updateCall(getLastRegistration.getRegistrationUuid());
    }
}
