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
    public UserAuth getUserAuth(int uid) {
        return userMapper.getUserAuth(uid);
    }

    @Override
    public UserAuth getUserAuthByWX(int openid) {
        return userMapper.getUserAuthByWX(openid);
    }

    @Override
    public void insertUserAuth(UserAuth userAuth) {
        userMapper.insertUserAuth(userAuth);
    }

    @Override
    public void updateUserAuth(UserAuth userAuth) {
        userMapper.updateUserAuth(userAuth);
    }

    @Override
    public void updateAuth(String auth) {
        userMapper.updateAuth(auth);
    }

    @Override
    public UserInfo getUserInfo(int uid) {
        return userMapper.getUserInfo(uid);
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userMapper.insertUserInfo(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userMapper.updateUserInfo(userInfo);
    }
}
