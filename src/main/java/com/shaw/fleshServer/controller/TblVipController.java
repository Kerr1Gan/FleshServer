package com.shaw.fleshServer.controller;

import com.google.gson.Gson;
import com.shaw.fleshServer.base.common.FleshResult;
import com.shaw.fleshServer.entity.Payment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TblVipController {

//    {
//        "client": {
//        "environment": "sandbox",
//                "paypal_sdk_version": "2.16.0",
//                "platform": "Android",
//                "product_name": "PayPal-Android-SDK"
//    },
//        "response": {
//        "create_time": "2018-03-09T15:29:40Z",
//                "id": "PAY-9V518331523893830LKRKQXA",
//                "intent": "sale",
//                "state": "approved"
//    },
//        "response_type": "payment"
//    }
//    {
//        "amount": "5",
//            "currency_code": "USD",
//            "short_description": "Vip充值",
//            "intent": "sale"
//    }

    @RequestMapping(value = "/isVip", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult getUserById(@RequestParam String userId, @RequestParam String paymentJson) {
        try {
            Payment payment = new Gson().fromJson(paymentJson, Payment.class);
            return new FleshResult("0", payment);
        } catch (Exception e) {
            e.printStackTrace();
            return new FleshResult("-1", null);
        }
    }

}
