package org.qiyu.hospital.config.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class Initialization {
    private static final Logger log = LoggerFactory.getLogger(Initialization.class);
    private final JdbcTemplate jdbcTemplate;
    private Operate operate;

    @PostConstruct
    public void setUp() {
        log.info("[INIT] 系统初始化");

        operate = new Operate(jdbcTemplate);

        this.roleInit();
    }

    private void roleInit() {
        log.debug("[INIT] 角色表初始化......");

        operate.roleInsert("ADMIN",  "管理员");
        operate.roleInsert("DOCTOR",  "医生");
        operate.roleInsert("PATIENT",  "患者");
    }
}
