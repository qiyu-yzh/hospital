package org.qiyu.hospital.service.logic;

import com.xlf.utility.util.UuidUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.mapper.TokenMapper;
import org.qiyu.hospital.model.entity.TokenDO;
import org.qiyu.hospital.service.TokenService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenLogic implements TokenService {
    private final TokenMapper tokenMapper;

    @Override
    public String createToken(String userUuid, Long expiredHourTime) {
        String token = UuidUtil.generateStringUuid();
        tokenMapper.insertToken(token, userUuid, new Timestamp(System.currentTimeMillis() + expiredHourTime * 3600 * 1000));
        return token;
    }

    @Override
    public void deleteToken(String token, HttpServletRequest request) {
        tokenMapper.deleteToken(token);
    }

    @Override
    public boolean clearToken(String userUuid) {
        return tokenMapper.deleteTokenByUserUuid(userUuid) > 0;
    }

    @Override
    public boolean verifyToken(String token) {
        TokenDO getToken = tokenMapper.getToken(token);
        if (getToken == null) {
            return false;
        }
        if (getToken.getExpiredAt().before(new Timestamp(System.currentTimeMillis()))) {
            tokenMapper.deleteToken(token);
            return false;
        }
        return true;
    }

    @Override
    public List<TokenDO> list(String userUuid) {
        return tokenMapper.selectTokenByUserUuid(userUuid);
    }
}
