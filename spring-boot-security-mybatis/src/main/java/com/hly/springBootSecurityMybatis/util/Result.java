package com.hly.springBootSecurityMybatis.util;

public class Result {

    private int resultCode;//结果代码 200成功 400错误的请求
    private String errorMsg;//错误消息
    private String errorReason;//错误原因
    private Object result;//响应的json字符串

    public Result() {
        this.resultCode = 200;//默认调用成功
    }

    public Result(int resultCode, String errorMsg, String errorReason, Object result) {
        super();
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.errorReason = errorReason;
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 仅设出错信息
     */
    public void setErrInfos(int resultCode, String errorMsg, String errorReason) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
        this.errorReason = errorReason;
    }

    /**
     * 请求参数校验出错
     */
    public void setParaErrInfos(String errorReason) {
        this.resultCode = 400;
        this.errorMsg = "请求参数校验失败！";
        this.errorReason = errorReason;
    }

    /**
     * 业务调用时执行失败
     */
    public void setBusErrInfos(String errorMsg, String errorReason) {
        this.resultCode = 100;
        this.errorMsg = errorMsg;
        this.errorReason = errorReason;
    }
}
