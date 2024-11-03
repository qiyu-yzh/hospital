package org.qiyu.hospital.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenDO {
    private String tokenUuid;
    private String userUuid;
    private String createdAt;
    private String expiredAt;
}
