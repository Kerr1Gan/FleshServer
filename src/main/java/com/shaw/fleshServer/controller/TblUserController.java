package com.shaw.fleshServer.controller;

import com.shaw.fleshServer.base.common.FleshResult;
import com.shaw.fleshServer.base.utils.Md5Util;
import com.shaw.fleshServer.entity.TblUser;
import com.shaw.fleshServer.service.TblUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/api")
public class TblUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TblUser.class);

    @Autowired
    private TblUserService tblUserService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public FleshResult getUserById(@RequestParam int userId) {
        TblUser tblUser = tblUserService.getUserById(userId);
        return new FleshResult("0", "接口调用成功", tblUser);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public FleshResult addTblUser(@RequestBody TblUser tblUser) {
        FleshResult fleshResult = new FleshResult();
        try {
            TblUser existUser = tblUserService.getUserByDeviceId(tblUser.getDeviceId());
            if (existUser != null) {
                tblUserService.updateUser(tblUser);
            } else {
                tblUserService.addUser(tblUser);
            }
        } catch (Exception e) {
            return new FleshResult("-1", e.getMessage());
        }
        fleshResult.setCode("0");
        return fleshResult;
    }

    @RequestMapping(value = "/getUserByDeviceId", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult getUserByDeviceId(@RequestParam String deviceId) {
        TblUser user = tblUserService.getUserByDeviceId(deviceId);
        if (user == null) {
            user = tblUserService.getUserByDeviceId(Md5Util.doMd5(deviceId));
            if (user != null) {
                return new FleshResult("0", "接口调用成功", user);
            }
            LOGGER.info("未找到设备[" + deviceId + "]所对应的用户");
            return new FleshResult("-1", "未找到设备[" + deviceId + "]所对应的用户");
        }
        return new FleshResult("0", "接口调用成功", user);
    }
}
