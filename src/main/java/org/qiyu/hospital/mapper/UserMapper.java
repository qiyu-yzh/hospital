package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qiyu.hospital.model.entity.UserDO;
import org.qiyu.hospital.model.vo.AuthRegisterVO;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_name = #{userName} OR phone = #{phone} OR email = #{email} LIMIT 1")
    UserDO getUser(AuthRegisterVO authRegisterVO);

    @Insert("""
            INSERT INTO user (uuid, user_name, password, phone, email, role) 
            VALUES (#{uuid}, #{userName}, #{password}, #{phone}, #{email}, #{role})
            """)
    void insertUser(UserDO newUser);
}
