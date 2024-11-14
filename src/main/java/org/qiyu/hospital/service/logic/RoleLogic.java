package org.qiyu.hospital.service.logic;


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
}
