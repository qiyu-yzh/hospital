package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qiyu.hospital.model.entity.RoleDO;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM role WHERE role_name = #{roleName} LIMIT 1")
    RoleDO getRoleByName(String roleName);

    @Select("SELECT * FROM role ")
    List<RoleDO> getRoleList();

    @Select("SELECT * FROM role WHERE role_uuid = #{uuid} LIMIT 1")
    RoleDO getRoleUuid(String uuid);

    @Select("SELECT * FROM role WHERE role_uuid = #{role} LIMIT 1")
    RoleDO getRoleByUuid(String role);
}
