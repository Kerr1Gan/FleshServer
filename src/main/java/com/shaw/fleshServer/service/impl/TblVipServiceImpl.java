package com.shaw.fleshServer.service.impl;

import com.shaw.fleshServer.service.TblVipService;
import com.shaw.fleshServer.utils.PayPalVerifyPayment;

public class TblVipServiceImpl implements TblVipService {

    @Override
    public boolean isPaySuccess(String paymentId) {
        PayPalVerifyPayment payment = new PayPalVerifyPayment();
        boolean success = false;
        try {
            success = payment.verifyPayment(paymentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(success ? "支付完成" : "支付校验失败");
        return success;
    }
}
