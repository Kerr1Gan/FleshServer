package com.shaw.fleshServer.controller;

import com.google.gson.Gson;
import com.shaw.fleshServer.base.common.FleshResult;
import com.shaw.fleshServer.entity.Payment;
import com.shaw.fleshServer.service.TblVipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TblVipController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TblVipController.class);

    @Autowired
    private TblVipService tblVipService;

    @RequestMapping(value = "/isVip", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult isVip(@RequestParam String deviceId, @RequestParam String paymentJson) {
        try {
            Payment payment = new Gson().fromJson(paymentJson, Payment.class);
            return new FleshResult("0", "", payment);
        } catch (Exception e) {
            e.printStackTrace();
            return new FleshResult("-1", null);
        }
    }

    @RequestMapping(value = "/verifyVip", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult verifyVip(@RequestParam String deviceId, @RequestParam String paymentJson) {
        return tblVipService.verifyVip(deviceId, paymentJson);
    }

    @RequestMapping(value = "/isPaySuccess", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult isPaySuccess(@RequestParam String deviceId, @RequestParam String paymentJson) {
        boolean result = tblVipService.isPaySuccess(deviceId, paymentJson);
        return new FleshResult(result == true ? "0" : "-1", "", result);
    }

}
