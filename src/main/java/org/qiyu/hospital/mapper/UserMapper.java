package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.*;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.entity.UserDO;
import org.qiyu.hospital.model.vo.AuthRegisterVO;
import org.qiyu.hospital.model.vo.UserLoginVO;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_name = #{userName} OR phone = #{phone} OR email = #{email} LIMIT 1")
    UserDO getUser(AuthRegisterVO authRegisterVO);

    @Select("SELECT COUNT(*) FROM user WHERE user_name = #{userName} OR phone = #{phone} OR email = #{email}")
    int checkUserExist(String userName, String phone, String email);

    @Insert("""
            INSERT INTO user (uuid, user_name, password, phone, email, role) 
            VALUES (#{uuid}, #{userName}, #{password}, #{phone}, #{email}, #{role})
            """)
    void insertUser(UserDO newUser);

    @Select("SELECT * FROM user WHERE user_name = #{username} LIMIT 1")
    UserDO getUserByUsername(String username);



    @Select("SELECT * FROM user")
    List<UserDO> getUserList();

    @Select("SELECT * FROM user WHERE user_name LIKE #{search} OR phone LIKE #{search} OR email LIKE #{search}")
    List<UserDO> getUserListBySearch(String search);

    @Delete("DELETE FROM user WHERE uuid = #{uuid}")
    void deleteUser(String uuid);

    @Select("SELECT * FROM user WHERE uuid = #{uuid}")
    UserDO getUserUuid(String uuid);

    @Insert("""
            INSERT INTO user (uuid, user_name, password, phone, email, role)
            VALUES (#{uuid}, #{userName}, #{password}, #{phone}, #{email}, #{role})
            """)
    void addUser(UserDO userDO);

    @Update("""
            UPDATE user SET user_name = #{userName}, password = #{password}, phone = #{phone}, email = #{email}, role = #{role}
            WHERE uuid = #{uuid}
            """)
    void updateUser(UserDO userDO);

}
