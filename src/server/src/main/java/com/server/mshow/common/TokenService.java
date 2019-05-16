package com.server.mshow.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


@Service("TokenService")
public class TokenService {

    @Value("${com.server.mshow.SECRET}")
    private String SECRET;

    @Autowired
    private  UserService userService;

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
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            open_id = jwt.getAudience().get(0);
            session_key = jwt.getClaim("session_key").asString();

            //验证session_key 过期f否，过期则更新session_key和token；
            System.out.println("open_id:  "+open_id);
            System.out.println("session_key: " + session_key);
            UserAuth userAuth = userService.getUserAuthByWX(open_id);
            if (userAuth == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }else{
                String user_session_key = userAuth.getSession_key();
                System.out.println("user_session_key: " + user_session_key);
                if(!user_session_key.equals(session_key)) {
                    userAuth.setSession_key(session_key);
                    userService.updateUserAuth(userAuth);
                    token = getToken(userAuth);
                }
            }


        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("open_id",open_id);
        map.put("session_key",session_key);
        map.put("token",token);//更新的token
        return map;
    }


}

