package com.shaw.fleshServer.service.impl;

import com.google.gson.Gson;
import com.shaw.fleshServer.base.common.FleshResult;
import com.shaw.fleshServer.base.utils.Md5Util;
import com.shaw.fleshServer.entity.Payment;
import com.shaw.fleshServer.entity.TblUser;
import com.shaw.fleshServer.mapper.TblUserMapper;
import com.shaw.fleshServer.service.TblVipService;
import com.shaw.fleshServer.utils.PayPalVerifyPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TblVipServiceImpl implements TblVipService {

    @Autowired
    private TblUserMapper userMapper;

    @Override
    public boolean isPaySuccess(String paymentId) {
        PayPalVerifyPayment payment = new PayPalVerifyPayment();
        int success = 0;
        try {
            success = payment.verifyPayment(paymentId, 5.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(success ? "支付完成" : "支付校验失败");
        return success == 1;
    }

    public FleshResult verifyVip(String deviceId, String paymentJson) {
        try {
            Payment payment = new Gson().fromJson(paymentJson, Payment.class);
            PayPalVerifyPayment verify = new PayPalVerifyPayment();
            int success = 0;
            int retryTime = 0;
            while (!Thread.interrupted() && retryTime <= 5) {
                success = verify.verifyPayment(payment.getConfirm().getResponse().getId(), 5.0);
                if (success == 1) {
                    TblUser user = userMapper.getUserByDeviceId(deviceId);
                    if (user != null) {
                        user.setIsVip("1");
                        user.setPaymentId(payment.getConfirm().getResponse().getId());
                        userMapper.updateUser(user);
                    } else {
                        user = new TblUser();
                        user.setUserName("");
                        user.setIsVip("1");
                        user.setPassword("");
                        user.setDeviceId(deviceId);
                        user.setPaymentId(payment.getConfirm().getResponse().getId());
                        userMapper.addUser(user);
                    }
                    break;
                } else if (success == 0) {
                    break;
                }
                retryTime++;
            }
            if (success == 0) {
                return new FleshResult("-1", "支付失败");
            }
            return new FleshResult("0", "支付成功", Md5Util.doMd5(payment.getConfirm().getResponse().getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new FleshResult("-1", "支付失败");
        }
    }
}
