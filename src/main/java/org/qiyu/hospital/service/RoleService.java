package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.RoleDTO;
import org.qiyu.hospital.model.entity.RoleDO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getRoleList();

    RoleDO getRoleByUuid(String role);

    boolean checkRoleHasAdmin(String role);
}
