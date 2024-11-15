package org.qiyu.hospital.service.logic;


import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import org.qiyu.hospital.mapper.RoleMapper;
import org.qiyu.hospital.model.dto.RoleDTO;
import org.qiyu.hospital.model.entity.RoleDO;
import org.qiyu.hospital.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleLogic implements RoleService {

    private final RoleMapper roleMapper;

    public RoleLogic(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> getRoleList() {
        List<RoleDO> roleList = roleMapper.getRoleList();
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (RoleDO roleDO : roleList) {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(roleDO, roleDTO);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    @Override
    public RoleDO getRoleByUuid(String role) {
        RoleDO roleDO = roleMapper.getRoleByUuid(role);
        if (roleDO == null) {
            throw new BusinessException("角色不存在", ErrorCode.NOT_EXIST);
        }
        return roleDO;
    }

    @Override
    public boolean checkRoleHasAdmin(String role) {
        RoleDO roleDO = roleMapper.getRoleByUuid(role);
        if (roleDO == null) {
            throw new BusinessException("角色不存在", ErrorCode.NOT_EXIST);
        }
        return "ADMIN".equals(roleDO.getRoleName());
    }
}
