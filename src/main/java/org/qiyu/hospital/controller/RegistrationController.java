package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.annotations.HasAuthorize;
import com.xlf.utility.annotations.HasRole;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qiyu.hospital.model.dto.DoctorDTO;
import org.qiyu.hospital.model.dto.RegistrationDTO;
import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.RegistrationVO;
import org.qiyu.hospital.service.DoctorService;
import org.qiyu.hospital.service.RegistrationService;
import org.qiyu.hospital.service.TypeService;
import org.qiyu.hospital.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final DoctorService doctorService;
    private final TypeService TypeService;
    private final UserService userService;

    @HasRole({"ADMIN"})
    @GetMapping("/console/list")
    public ResponseEntity<BaseResponse<List<RegistrationDTO>>> consoleGetRegistrationList() {
        log.debug("获取挂号列表");
        List<RegistrationDTO> registrationList = registrationService.consoleGetRegistrationList();
        return ResultUtil.success("获取挂号列表成功", registrationList);
    }

    @HasAuthorize
    @PostMapping("/add")
    public ResponseEntity<BaseResponse<Void>> addRegistration(
            @RequestBody @Validated RegistrationVO registrationVO,
            @NonNull HttpServletRequest request
    ) throws ParseException {
        String userToken = HeaderUtil.getAuthorizeUserUuidString(request);
        UserDTO userDTO = userService.getUserByTokenUuid(userToken);
        DoctorDTO doctor = doctorService.getDoctor(registrationVO.getDoctor());
        TypeDTO type = TypeService.getType(registrationVO.getType());
        if (!doctor.getType().equals(type.getTypeUuid()))
           throw new BusinessException("该科室中没有此医生", ErrorCode.FORBIDDEN);
        registrationService.addRegistration(userDTO, registrationVO);
        return ResultUtil.success("添加挂号成功");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<BaseResponse<Void>> deleteRegistration(@PathVariable String uuid) {
        registrationService.deleteRegistration(uuid);
        return ResultUtil.success("删除挂号成功");
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BaseResponse<RegistrationDTO>> getRegistration(
            @PathVariable("uuid") String uuid) {
        RegistrationDTO registration = registrationService.getRegistration(uuid);
        return ResultUtil.success("获取挂号成功", registration);
    }

    @HasAuthorize
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<RegistrationDTO>>> getRegistrationList(
            @NonNull HttpServletRequest request
    ) {
        UserDTO userDTO = userService.getUserByTokenUuid(HeaderUtil.getAuthorizeUserUuidString(request));
        List<RegistrationDTO> registrationList = registrationService.getRegistrationList(userDTO);
        return ResultUtil.success("获取挂号列表成功", registrationList);
    }

    @GetMapping("/doctor/list")
    public ResponseEntity<BaseResponse<List<RegistrationDTO>>> getRegistrationsByDoctor(String doctorUuid) {
        List<RegistrationDTO> registrationList = registrationService.getRegistrationsByDoctor(doctorUuid);
        return ResultUtil.success("获取医生挂号记录成功", registrationList);
    }
}


