package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qiyu.hospital.model.entity.DoctorDO;

import java.util.List;

@Mapper
public interface DoctorMapper {

    @Select("SELECT * FROM doctor")
    List<DoctorDO> getDoctorList();

    @Select("SELECT COUNT(*) FROM doctor WHERE real_name = #{realName}")
    int checkDoctorExist(String realName);


    @Insert("""
            INSERT INTO doctor (doctor_uuid, real_name,college,date_birth,expert,attending,description,user,type)
            VALUES (#{doctorUuid}, #{realName}, #{college}, #{dateBirth}, #{expert}, #{attending}, #{description}, #{user}, #{type})
            """)
    void addDoctor(DoctorDO newDoctor);


    @Select("SELECT * FROM doctor WHERE doctor_uuid = #{doctorUuid}")
    DoctorDO getDoctorUuid(String doctorUuid);

    @Delete("DELETE FROM doctor WHERE doctor_uuid = #{doctorUuid}")
    void deleteDoctor(String doctorUuid);
}
