package com.server.mshow.dao;

import com.server.mshow.domain.UserAuth;
import com.server.mshow.domain.UserInfo;

public interface UserMapper {

    UserAuth getUserAuth(String uid);
    UserAuth getUserAuthBywx(String openid);
    void insertUserAuth(UserAuth userAuth);
    void updateUserAuth(UserAuth userAuth);
    void updateAuth(String auth);

    UserInfo getUserInfo(String uid);
    void insertUserInfo(UserInfo UserInfo);
    void updateUserInfo(UserInfo userInfo);
}
