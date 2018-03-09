package com.shaw.fleshServer.service;

import com.shaw.fleshServer.entity.TblUser;

public interface TblUserService {
    public TblUser getUserById(int userId);
    public int addUser(TblUser tblUser);
}
