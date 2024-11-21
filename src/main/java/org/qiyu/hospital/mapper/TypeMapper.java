package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.*;
import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.entity.TypeDO;

import java.util.List;

@Mapper
public interface TypeMapper {
     @Select("SELECT * FROM type")
     List<TypeDO> getTypeList();

     @Select("SELECT * FROM type WHERE type_uuid = #{uuid}")
     TypeDO getTypeUuid(String uuid);

     @Delete("DELETE FROM type WHERE type_uuid = #{uuid}")
     void deleteType(String uuid);

     @Select("SELECT COUNT(*) FROM type WHERE outpatient_type = #{outpatientType}")
     int checkTypeExist(String outpatientType);

     @Insert("""
             INSERT INTO type (type_uuid, outpatient_type, description) 
             VALUES (#{typeUuid}, #{outpatientType}, #{description})
             """)
     void addType(TypeDO typeDO);

     @Update("""
             UPDATE type SET outpatient_type = #{outpatientType}, description = #{description}
             WHERE type_uuid = #{typeUuid}
             """)
     void updateType(TypeDO typeDO);
}
