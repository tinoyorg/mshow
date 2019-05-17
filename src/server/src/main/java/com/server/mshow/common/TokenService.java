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
        token= JWT.create().withClaim("openid",userAuth.getOpenid())
                .withClaim("uid",userAuth.getUid())// 将 Openid 保存到 token 里面
                .sign(Algorithm.HMAC256(SECRET));// 以 SECRET 作为 token 的密钥
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
        int uid;
        String session_key;
        String session_keyByUid;
//        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            open_id = jwt.getClaim("openid").asString();
//            uid = jwt.getClaim("uid").asInt();
//            UserAuth userAuthByWX = userService.getUserAuthByWX(open_id);
//            UserAuth userAuthByUid = userService.getUserAuth(uid);
//            System.out.println("open_id:  "+open_id);
//
//            if (userAuthByWX == null || userAuthByUid == null) {
//                throw new RuntimeException("用户不存在，请重新登录");
//            }else{
//                session_key = userAuthByWX.getSession_key();
//                session_keyByUid = userAuthByUid.getSession_key();
//                if (session_key.equals(session_keyByUid)) {
//                    System.out.println("session_key: " + session_key);
//                    System.out.println("session_keyByUid: " + session_keyByUid);
//                }else {
//                    System.out.println("用户信息不匹配");
//                    throw new RuntimeException("401");
//                }
//            }
//
//        } catch (JWTDecodeException j) {
//            throw new RuntimeException("401");
//        }

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("open_id",open_id);
       // map.put("session_key",session_key);
        map.put("token",token);//更新的token
        return map;
    }


}

