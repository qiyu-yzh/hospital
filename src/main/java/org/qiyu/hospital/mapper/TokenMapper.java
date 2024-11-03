package org.qiyu.hospital.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qiyu.hospital.model.entity.TokenDO;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface TokenMapper {

    @Insert("INSERT INTO token (token_uuid, user_uuid, expired_at) VALUES (#{token}, #{userUuid}, #{timestamp})")
    void insertToken(String token, String userUuid, Timestamp timestamp);

    @Select("SELECT * FROM token WHERE token_uuid = #{token} LIMIT 1")
    TokenDO getToken(String token);

    @Delete("DELETE FROM token WHERE token_uuid = #{token}")
    void deleteToken(String token);

    @Delete("DELETE FROM token WHERE user_uuid = #{userUuid}")
    int deleteTokenByUserUuid(String userUuid);

    @Select("SELECT * FROM token WHERE user_uuid = #{userUuid}")
    List<TokenDO> selectTokenByUserUuid(String userUuid);
}
