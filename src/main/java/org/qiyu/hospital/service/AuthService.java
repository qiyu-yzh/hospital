package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.AuthUserDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.AuthRegisterVO;
import org.qiyu.hospital.model.vo.UserLoginVO;

public interface AuthService {
    /**
     * 检查用户是否存在
     * @param authRegisterVO 用户注册信息
     */
    void checkUserExist(AuthRegisterVO authRegisterVO);
    void checkUser(UserLoginVO userLoginVO);
    UserDTO userRegister(AuthRegisterVO authRegisterVO);
    UserDTO userLogin(UserLoginVO userLoginVO);
}
