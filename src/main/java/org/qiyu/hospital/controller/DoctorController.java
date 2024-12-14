package org.qiyu.hospital.controller;


import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.dto.DoctorDTO;
import org.qiyu.hospital.model.dto.DoctorDetailedDTO;
import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.DoctorAddVO;
import org.qiyu.hospital.model.vo.DoctorEditVO;
import org.qiyu.hospital.service.DoctorService;
import org.qiyu.hospital.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DoctorController {
    private final DoctorService doctorService;
    private final TypeService typeService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<DoctorDTO>>> getDoctorList() {
        List<DoctorDTO> doctorList = doctorService.getDoctorList();
        return ResultUtil.success("获取医生列表成功", doctorList);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<Void>> addDoctor(@RequestBody @Validated DoctorAddVO doctorAddVO) throws ParseException {
        doctorService.addDoctor(doctorAddVO);
        return ResultUtil.success("添加医生成功");
    }

    @GetMapping("/{doctor_uuid}")
    public ResponseEntity<BaseResponse<DoctorDetailedDTO>> getDoctor(
            @PathVariable("doctor_uuid") String doctorUuid) {
        DoctorDTO doctor = doctorService.getDoctor(doctorUuid);
        TypeDTO type = typeService.getType(doctor.getType());
        DoctorDetailedDTO doctorDetailedDTO = new DoctorDetailedDTO(doctor, type);
        return ResultUtil.success("获取医生成功", doctorDetailedDTO);
    }

    @DeleteMapping("/{doctor_uuid}")
    public ResponseEntity<BaseResponse<Void>> deleteDoctor(@PathVariable("doctor_uuid") String doctorUuid) {
        doctorService.deleteDoctor(doctorUuid);
        return ResultUtil.success("删除医生成功");
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse<Void>> updateDoctor(@RequestBody @Validated DoctorEditVO doctorEditVO) {
        doctorService.updateDoctor(doctorEditVO);
        return ResultUtil.success("更新医生成功");
    }

    @PatchMapping("/house_calls")
    public ResponseEntity<BaseResponse<Void>> houseCalls(
            @RequestParam("user_uuid") String user_uuid,
            @RequestParam("real_name") String realName
    ) {
        if (!user_uuid.matches("^[0-9a-f-]+$")) {
            throw new BusinessException("用户uuid格式错误", ErrorCode.PARAMETER_INVALID);
        }
        if (realName.length() < 2 || realName.length() > 10) {
            throw new BusinessException("真实姓名格式错误", ErrorCode.PARAMETER_INVALID);
        }
        doctorService.houseCalls(user_uuid, realName);
        return ResultUtil.success("更新医生成功");
    }

}
