package com.server.mshow.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.server.mshow.domain.UserAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


@Service("TokenService")
public class TokenService {

    @Value("${com.server.mshow.SECRET}")
    private String SECRET;

    public String getToken(UserAuth userAuth) {
        String token="";
        token= JWT.create().withAudience(userAuth.getOpenid())
                .withClaim("session_key",userAuth.getSession_key())// 将 Openid 保存到 token 里面
                .sign(Algorithm.HMAC256(SECRET));// 以 Session_key 作为 token 的密钥
        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public LinkedHashMap<String, String> verifyToken(String token) {

        String open_id;
        String session_key ;
        try {
            open_id = JWT.decode(token).getAudience().get(0);
            session_key = JWT.decode(token).getClaim("session_key").asString();
            System.out.println("verifyid:  "+open_id);
            System.out.println("verifykey: " + session_key);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("openid",open_id);
        map.put("session_key",session_key);

        return map;
    }


}

