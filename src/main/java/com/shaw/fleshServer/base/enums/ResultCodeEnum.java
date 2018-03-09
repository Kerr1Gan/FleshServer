package com.shaw.fleshServer.base.enums;

import java.text.MessageFormat;

/**
 *  响应代码枚举
 */
public enum ResultCodeEnum {

    SUCCESS("0000","{0}调用成功")
    ;

    private String code;
    private String resultMsg;

    ResultCodeEnum(String code, String resultMsg) {
        this.code = code;
        this.resultMsg = resultMsg;
    }

    /**
     * 绑定参数，可变入参
     * @param arg1
     * @return
     */
    public String getResultMsg(Object... arg1){
        return MessageFormat.format(resultMsg,arg1);
    }
}
