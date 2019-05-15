package com.server.mshow.service;


import com.server.mshow.domain.UserAuth;
import com.server.mshow.domain.UserInfo;

public interface UserService {

    UserAuth getUserAuth(int uid);
    UserAuth getUserAuthBywx(int openid);
    void insertUserAuth(UserAuth userAuth);
    void updateUserAuth(UserAuth userAuth);
    void updateAuth(String auth);

    UserInfo getUserInfo(int uid);
    void insertUserInfo(UserInfo UserInfo);
    void updateUserInfo(UserInfo userInfo);

}
