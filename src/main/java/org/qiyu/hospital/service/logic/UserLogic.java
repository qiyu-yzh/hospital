package org.qiyu.hospital.service.logic;

import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import org.qiyu.hospital.mapper.RoleMapper;
import org.qiyu.hospital.mapper.TokenMapper;
import org.qiyu.hospital.mapper.UserMapper;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.entity.RoleDO;
import org.qiyu.hospital.model.entity.TokenDO;
import org.qiyu.hospital.model.entity.UserDO;
import org.qiyu.hospital.model.vo.ConsoleUserAddVO;
import org.qiyu.hospital.model.vo.ConsoleUserEditVO;
import org.qiyu.hospital.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLogic implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final TokenMapper tokenMapper;

    public UserLogic(UserMapper userMapper, RoleMapper roleMapper, TokenMapper tokenMapper, TokenMapper tokenMapper1) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.tokenMapper = tokenMapper1;
    }

    @Override
    public List<UserDTO> getUserList() {
        List<UserDO> userList = userMapper.getUserList();
//        return userList.stream().map(userDO -> {
//            UserDTO userDTO = new UserDTO();
//            BeanUtils.copyProperties(userDO, userDTO);
//            return userDTO;
//        }).toList();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (UserDO userDO : userList) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDO, userDTO);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public void deleteUser(String uuid) {
        UserDO userDO = userMapper.getUserUuid(uuid);
        if (userDO == null) {
            throw new BusinessException("用户不存在", ErrorCode.OPERATION_DENIED);
        }
        userMapper.deleteUser(uuid);
    }

    @Override
    public void addUser(ConsoleUserAddVO consoleUserAddVO) {
        int userDO = userMapper.checkUserExist(consoleUserAddVO.getUserName(), consoleUserAddVO.getPhone(), consoleUserAddVO.getEmail());
        if (userDO != 0) {
            throw new BusinessException("用户已存在", ErrorCode.OPERATION_DENIED);
        }
        UserDO newUser = new UserDO();
        BeanUtils.copyProperties(consoleUserAddVO, newUser);
        RoleDO getPatientDO = roleMapper.getRoleUuid(consoleUserAddVO.getRole());
        if (getPatientDO == null) {
            throw new BusinessException("角色为空", ErrorCode.SERVER_INTERNAL_ERROR);
        }
        newUser.setUuid(UuidUtil.generateStringUuid())
                .setRole(getPatientDO.getRoleUuid());
        userMapper.addUser(newUser);
    }

    @Override
    public void updateUser(ConsoleUserEditVO consoleUserEditVO) {
        UserDO userDO = userMapper.getUserUuid(consoleUserEditVO.getUuid());
        if (userDO == null) {
            throw new BusinessException("用户不存在", ErrorCode.OPERATION_DENIED);
        }
        UserDO editUser = new UserDO();
        BeanUtils.copyProperties(consoleUserEditVO, editUser);
        RoleDO getPatientDO = roleMapper.getRoleUuid(consoleUserEditVO.getRole());
        if (getPatientDO == null) {
            throw new BusinessException("角色为空", ErrorCode.SERVER_INTERNAL_ERROR);
        }
        editUser.setRole(getPatientDO.getRoleUuid());
        userMapper.updateUser(editUser);
    }

    @Override
    public UserDTO getUserByTokenUuid(String userToken) {
        TokenDO getToken = tokenMapper.getToken(userToken);
        if (getToken == null) {
            throw new BusinessException("用户未登录", ErrorCode.FORBIDDEN);
        }
        UserDO getUser = userMapper.getUserUuid(getToken.getUserUuid());
        if (getUser == null) {
            throw new BusinessException("用户不存在", ErrorCode.OPERATION_DENIED);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(getUser, userDTO);
        return userDTO;
    }


}
