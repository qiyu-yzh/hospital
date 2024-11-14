package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.ConsoleUserAddVO;
import org.qiyu.hospital.model.vo.ConsoleUserEditVO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();

    void deleteUser(String uuid);

    void addUser(ConsoleUserAddVO consoleUserAddVO);

    void updateUser(ConsoleUserEditVO consoleUserEditVO);

    UserDTO getUserByTokenUuid(String userToken);
}
