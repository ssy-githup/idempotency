package ai.ssy.service.impl;

import ai.ssy.common.Constant;
import ai.ssy.service.TokenService;
import ai.ssy.util.JedisUtil;
import ai.ssy.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public String createToken() {
        String str = RandomUtil.UUID32();
        StrBuilder token = new StrBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);

        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);

        return token.toString();
    }

    @Override
    public String checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        // header中不存在token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            // parameter中也不存在token
            if (StringUtils.isBlank(token)) {
                return "不存在token";
            }
        }

        if (!jedisUtil.exists(token)) {
            return "redis不存在token";
        }

        Long del = jedisUtil.del(token);
        if (del <= 0) {
            return "redis删除token失败" ;
        }
        return "0";
    }

}
