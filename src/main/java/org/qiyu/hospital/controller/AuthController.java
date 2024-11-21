package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.dto.AuthUserDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.AuthRegisterVO;
import org.qiyu.hospital.model.vo.UserLoginVO;
import org.qiyu.hospital.service.AuthService;
import org.qiyu.hospital.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<AuthUserDTO>> userRegister(
            @RequestBody @Validated AuthRegisterVO authRegisterVO
    ) {
        // TODO: 用户注册
        //    1. 获取用户数据
        //    2. VO -> Mapper 查数据
        //    3. 查到数据检查是否重复
        //    4. 数据查不到，可以插入
        //    5. 登录授权
        //    6. 完成注册
        if (authRegisterVO.getEmail().isEmpty()) {
            authRegisterVO.setEmail(null);
        }
        authService.checkUserExist(authRegisterVO);
        UserDTO getUser = authService.userRegister(authRegisterVO);
        String getToken = tokenService.createToken(getUser.getUuid(), 24L);
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO
                .setUser(getUser)
                .setToken(getToken);
        return ResultUtil.success("注册成功", authUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthUserDTO>> userLogin(
            @RequestBody @Validated UserLoginVO userLoginVO) {
        authService.checkUser(userLoginVO);
        UserDTO getUser = authService.userLogin(userLoginVO);
        String getToken = tokenService.createToken(getUser.getUuid(), 24L);
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUser(getUser)
                .setToken(getToken);
        return ResultUtil.success("登录成功", authUserDTO);
    }
}
