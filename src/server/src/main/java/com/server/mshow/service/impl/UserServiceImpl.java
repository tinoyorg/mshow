package com.server.mshow.service.impl;

import com.server.mshow.dao.UserMapper;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.domain.UserInfo;
import com.server.mshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    //@Autowired
    private  UserMapper userMapper;

    @Override
    public UserAuth getUserAuth(String uid) {
        return null;
    }

    @Override
    public UserAuth getUserAuthBywx(String openid) {
        return null;
    }

    @Override
    public void insertUserAuth(UserAuth userAuth) {

    }

    @Override
    public void updateUserAuth(UserAuth userAuth) {

    }

    @Override
    public void updateAuth(String auth) {

    }

    @Override
    public UserInfo getUserInfo(String uid) {
        return null;
    }

    @Override
    public void insertUserInfo(UserInfo UserInfo) {

    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

    }
}
