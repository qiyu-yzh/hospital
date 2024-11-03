package org.qiyu.hospital.service;

import org.qiyu.hospital.model.entity.UserDO;
import org.qiyu.hospital.model.vo.AuthRegisterVO;

public interface AuthService {
    void checkUserExist(AuthRegisterVO authRegisterVO);

    UserDO userRegister(AuthRegisterVO authRegisterVO);
}
