package com.shaw.fleshServer.service;

import com.shaw.fleshServer.base.common.FleshResult;

public interface TblVipService {
    boolean isPaySuccess(String paymentId);

    FleshResult verifyVip(String deviceId, String paymentJson);
}
