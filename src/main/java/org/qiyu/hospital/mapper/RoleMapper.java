package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qiyu.hospital.model.entity.RoleDO;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM role WHERE role_name = #{roleName} LIMIT 1")
    RoleDO getRoleByName(String roleName);
}
