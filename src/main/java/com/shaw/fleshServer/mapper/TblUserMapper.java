package com.shaw.fleshServer.mapper;

import com.shaw.fleshServer.entity.TblUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblUserMapper {
    TblUser getUserById(int userId);
}
