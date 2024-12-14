package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.*;
import org.qiyu.hospital.model.entity.DoctorDO;
import org.qiyu.hospital.model.entity.RegistrationDO;

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

    @Update("""
            UPDATE doctor SET real_name = #{realName}, college = #{college}, date_birth = #{dateBirth}, expert = #{expert}, attending = #{attending}, description = #{description}, user = #{user}, type = #{type}
            WHERE doctor_uuid = #{doctorUuid}
            """)
    void updateDoctor(DoctorDO doctorDO);

    @Update("""
            UPDATE registration SET is_final = 1 WHERE registration_uuid = #{registrationUuid}
            """)
    void updateCall(String registrationUuid);


    @Select("SELECT * FROM doctor WHERE user = #{uuid}")
    DoctorDO getDoctorByUserUuid(String uuid);
}
