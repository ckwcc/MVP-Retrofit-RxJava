package com.ckw.zfsoft.simplemvp.netloader;

/**
 * Created by ckw
 * on 2017/11/29.
 */

public class HttpResult<T>{

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return code == 0;
    }

//    @SerializedName("status")
//    public int status;
//
//    @SerializedName("msg")
//    public String msg;
//
//    @SerializedName("data")
//    public T t;

}
