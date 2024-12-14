package org.qiyu.hospital.service.logic;

import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.qiyu.hospital.mapper.RegistrationMapper;
import org.qiyu.hospital.model.dto.RegistrationDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.entity.RegistrationDO;
import org.qiyu.hospital.model.vo.RegistrationVO;
import org.qiyu.hospital.service.RegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class RegistrationLogic implements RegistrationService {

    private final RegistrationMapper registrationMapper;

    public RegistrationLogic(org.qiyu.hospital.mapper.RegistrationMapper registrationMapper) {
        this.registrationMapper = registrationMapper;
    }

    @Override
    public void addRegistration(UserDTO userDTO, RegistrationVO registrationVO) throws ParseException {
        int registrationExist = registrationMapper.checkRegistrationExist(registrationVO.getRealName());
        if (registrationExist != 0) {
            throw new BusinessException("挂号已存在", ErrorCode.OPERATION_DENIED);
        }
        Random random = new Random();
        RegistrationDO newRegistration = new RegistrationDO();
        log.debug("registrationVO: {}", registrationVO);
        newRegistration
                .setRegistrationUuid(UuidUtil.generateStringUuid())
                .setGender(registrationVO.getGender())
                .setAge(registrationVO.getAge())
                .setRealName(registrationVO.getRealName())
                .setDoctor(registrationVO.getDoctor())
                .setType(registrationVO.getType())
                .setUser(userDTO.getUuid())
                .setTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(registrationVO.getTime()).getTime()))
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .setPrice((double) random.nextInt(100));
        log.debug("newRegistration: {}", newRegistration);
        registrationMapper.addRegistration(newRegistration);
    }

    @Override
    public List<RegistrationDTO> consoleGetRegistrationList() {
        List<RegistrationDO> registrationList = registrationMapper.getRegistrationList();
        return getRegistrationDTOS(registrationList);
    }

    @Override
    public void deleteRegistration(String uuid) {
        RegistrationDO registrationDO = registrationMapper.getRegistrationUuid(uuid);
        if (registrationDO == null) {
            throw new BusinessException("挂号不存在", ErrorCode.OPERATION_DENIED);
        }
        registrationMapper.deleteRegistration(uuid);
    }

    @Override
    public RegistrationDTO getRegistration(String uuid) {
        RegistrationDO registrationDO = registrationMapper.getRegistrationUuid(uuid);
        if (registrationDO == null) {
            throw new BusinessException("挂号不存在", ErrorCode.OPERATION_DENIED);
        }
        RegistrationDTO registrationDTO = new RegistrationDTO();
        BeanUtils.copyProperties(registrationDO, registrationDTO);
        return registrationDTO;
    }

    @Override
    public List<RegistrationDTO> getRegistrationList(UserDTO userDTO) {
        log.debug("userDTO: {}", userDTO);
        List<RegistrationDO> registrationList = registrationMapper.getRegistrationListByUser(userDTO.getUuid());
        log.debug("registrationList: {}", registrationList);
        return getRegistrationDTOS(registrationList);
    }

    @Override
    public List<RegistrationDTO> getRegistrationsByDoctor(String doctorUuid) {
        List<RegistrationDO> registrationList = registrationMapper.getRegistrationListByDoctor(doctorUuid);
        return getRegistrationDTOS(registrationList);

    }


    @NotNull
    private static List<RegistrationDTO> getRegistrationDTOS(@NotNull List<RegistrationDO> registrationList) {
        List<RegistrationDTO> registrationDTOList = new ArrayList<>();
        for (RegistrationDO registrationDO : registrationList) {
            RegistrationDTO registrationDTO = new RegistrationDTO();
            BeanUtils.copyProperties(registrationDO, registrationDTO);
            registrationDTOList.add(registrationDTO);
        }
        return registrationDTOList;
    }
}
