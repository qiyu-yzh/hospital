package org.qiyu.hospital.service.logic;

import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.mapper.RoleMapper;
import org.qiyu.hospital.mapper.UserMapper;
import org.qiyu.hospital.model.entity.RoleDO;
import org.qiyu.hospital.model.entity.UserDO;
import org.qiyu.hospital.model.vo.AuthRegisterVO;
import org.qiyu.hospital.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLogic implements AuthService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public void checkUserExist(AuthRegisterVO authRegisterVO) {
        UserDO getUser = userMapper.getUser(authRegisterVO);
        // 用户存在
        if (getUser != null) {
            throw new BusinessException("该用户信息已被注册", ErrorCode.OPERATION_DENIED);
        }
    }

    @Override
    public void userRegister(AuthRegisterVO authRegisterVO) {
        UserDO newUser = new UserDO();
        BeanUtils.copyProperties(authRegisterVO, newUser);

        // 获取默认角色（患者）
        RoleDO getPatientDO = roleMapper.getRoleByName("PATIENT");
        if (getPatientDO == null) {
            throw new BusinessException("角色为空", ErrorCode.SERVER_INTERNAL_ERROR);
        }
        newUser
                .setUuid(UuidUtil.generateStringUuid())
                .setRole(getPatientDO.getRoleUuid());

        userMapper.insertUser(newUser);
    }
}
