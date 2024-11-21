package org.qiyu.hospital.service.logic;

import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import org.qiyu.hospital.mapper.DoctorMapper;
import org.qiyu.hospital.model.dto.DoctorDTO;
import org.qiyu.hospital.model.entity.DoctorDO;
import org.qiyu.hospital.model.vo.DoctorAddVO;
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
public class DoctorLogic implements DoctorService {

    private static final Logger log = LoggerFactory.getLogger(DoctorLogic.class);
    private final DoctorMapper doctorMapper;

    public DoctorLogic(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

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
                .setDateBirth(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(doctorAddVO.getDateBirth()).getTime()));
        doctorMapper.addDoctor(newDoctor);
    }
}
