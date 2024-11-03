package org.qiyu.hospital.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenDO {
    private String tokenUuid;
    private String userUuid;
    private Timestamp createdAt;
    private Timestamp expiredAt;
}
