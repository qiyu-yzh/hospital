package org.qiyu.hospital.config.init;

import com.xlf.utility.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.entity.RoleDO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.management.relation.Role;

@RequiredArgsConstructor
public class Operate {
    private final JdbcTemplate jdbcTemplate;

    protected void roleInsert(String roleName, String nickname) {
        try {
            jdbcTemplate.queryForObject("SELECT * FROM role WHERE role_name = ? LIMIT 1", new BeanPropertyRowMapper<>(RoleDO.class), roleName);
        } catch (EmptyResultDataAccessException e) {
            jdbcTemplate.update("INSERT INTO role (role_uuid, role_name, nickname) VALUES (?, ? ,?)",
                    UuidUtil.generateStringUuid(),
                    roleName,
                    nickname
            );
        }
    }
}
