package com.shaw.fleshServer.base.common;

/**
 * 返回参数封装
 */
public class FleshResult <T>{

    private String code;
    private String msg;
    private T data;

    public FleshResult() {
    }

    public FleshResult(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public FleshResult(String code, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
