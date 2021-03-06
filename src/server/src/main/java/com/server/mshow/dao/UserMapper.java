package com.server.mshow.dao;

import com.server.mshow.domain.UserAuth;
import com.server.mshow.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserAuth getUserAuth(int uid);
    UserAuth getUserAuthByWX(String openid);
    void insertUserAuth(UserAuth userAuth);
    void updateUserAuth(UserAuth userAuth);
    void updateAuth(String auth);

    UserInfo getUserInfo(int uid);
    void insertUserInfo(UserInfo UserInfo);
    void updateUserInfo(UserInfo userInfo);
}
