package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.ConsoleUserAddVO;
import org.qiyu.hospital.model.vo.ConsoleUserEditVO;
import org.qiyu.hospital.model.vo.UserEditVO;
import org.qiyu.hospital.model.vo.UserUpdatePasswordVO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();

    void deleteUser(String uuid);

    UserDTO addUser(ConsoleUserAddVO consoleUserAddVO);

    void consoleUpdateUser(ConsoleUserEditVO consoleUserEditVO);

    void updateUser(UserEditVO userEditVO);

    void updatePassword(UserUpdatePasswordVO userUpdatePasswordVO);

    UserDTO getUserByTokenUuid(String userToken);
}
