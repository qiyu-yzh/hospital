package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.RoleDTO;
import org.qiyu.hospital.model.dto.UserDTO;

import java.util.List;
public interface RoleService {
    List<RoleDTO> getRoleList();
}
