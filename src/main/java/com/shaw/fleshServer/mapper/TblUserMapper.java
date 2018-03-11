package com.shaw.fleshServer.mapper;

import com.shaw.fleshServer.entity.TblUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TblUserMapper {
    TblUser getUserById(int userId);

    int addUser(TblUser user);

    int updateUser(TblUser user);

    TblUser getUserByDeviceId(String deviceId);
}
