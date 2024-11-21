package org.qiyu.hospital.controller;


import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.dto.DoctorDTO;
import org.qiyu.hospital.model.dto.DoctorDetailedDTO;
import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.vo.DoctorAddVO;
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
}
