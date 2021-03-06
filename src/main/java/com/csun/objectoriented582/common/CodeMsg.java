package com.csun.objectoriented582.common;

public class CodeMsg {

    private int code;
    private String msg;

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

    /*服务端异常*/
    public static CodeMsg SUCCESS = new CodeMsg(200, "SUCCESS");
    public static CodeMsg SERVER_ERROR = new CodeMsg(400, "System Exception：%s");
    public static CodeMsg BIND_ERROR = new CodeMsg(101, "(绑定异常)参数校验异常：%s"); /*用占位符 传入一个参数*/
    public static CodeMsg SESSION_ERROR = new CodeMsg(102, "No Session!"); /*用占位符 传入一个参数*/
    public static CodeMsg REQUEST_ERROR = new CodeMsg(103, "Invalid Request!"); /*用占位符 传入一个参数*/
    public static CodeMsg REQUEST_OVER_LIMIT = new CodeMsg(104, "Request too much!"); /*用占位符 传入一个参数*/

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CodeMsg(String msg) {
        this.msg = msg;
    }

    //不定参的构造函数
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
