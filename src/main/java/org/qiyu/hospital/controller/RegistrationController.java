package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.vo.RegistrationVO;
import org.qiyu.hospital.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistrationController {
    private final RegistrationService registrationService;


    @PostMapping("/add")
    public ResponseEntity<BaseResponse<Void>> addRegistration(@RequestBody @Validated RegistrationVO registrationVO) {
        registrationService.addRegistration(registrationVO);
        return ResultUtil.success("添加挂号成功");
    }
}

