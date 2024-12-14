package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qiyu.hospital.model.entity.RegistrationDO;
import org.qiyu.hospital.model.vo.RegistrationVO;

import java.util.List;

@Mapper
public interface RegistrationMapper {

    @Select("SELECT * FROM registration")
    List<RegistrationDO> getRegistrationList();

    @Select("SELECT COUNT(*) FROM registration WHERE real_name = #{realName} AND is_final = 0")
    int checkRegistrationExist(String realName);

    @Insert("""
            INSERT INTO registration (registration_uuid, user, doctor, type, price, time, real_name,age,gender,created_at)
            VALUES (#{registrationUuid}, #{user}, #{doctor}, #{type}, #{price}, #{time}, #{realName}, #{age},#{gender},#{createdAt})
            """)
    void addRegistration(RegistrationDO newRegistration);

    @Select("SELECT * FROM registration WHERE registration_uuid = #{uuid}")
    RegistrationDO getRegistrationUuid(String uuid);


    @Delete("DELETE FROM registration WHERE registration_uuid = #{uuid}")
    void deleteRegistration(String uuid);

    @Select("SELECT * FROM registration WHERE user = #{uuid}")
    List<RegistrationDO> getRegistrationListByUser(String uuid);

    @Select("SELECT * FROM registration WHERE user = #{userUuid} AND real_name = #{realName} AND is_final = 0 ORDER BY created_at DESC LIMIT 1")
    RegistrationDO getLastRegistration(String userUuid, String realName);

    @Select("SELECT * FROM registration WHERE doctor = #{doctorUuid}")
    List<RegistrationDO> getRegistrationListByDoctor(String doctorUuid);
}
