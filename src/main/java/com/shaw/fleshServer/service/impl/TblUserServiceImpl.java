package com.shaw.fleshServer.service.impl;

import com.shaw.fleshServer.entity.TblUser;
import com.shaw.fleshServer.mapper.TblUserMapper;
import com.shaw.fleshServer.service.TblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class TblUserServiceImpl implements TblUserService, Serializable  {

    @Autowired
    private TblUserMapper tblUserMapper;

    @Override
    public TblUser getUserById(int userId) {
        return tblUserMapper.getUserById(userId);
    }

    @Override
    public int addUser(TblUser tblUser) {
        return tblUserMapper.addUser(tblUser);
    }

    @Override
    public int updateUser(TblUser user) {
        return tblUserMapper.updateUser(user);
    }

    @Override
    public TblUser getUserByDeviceId(String deviceId) {
        return tblUserMapper.getUserByDeviceId(deviceId);
    }
}
