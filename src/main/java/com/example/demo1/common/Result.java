package com.example.demo1.common;

public class Result<T> {

    private int code;        // 状态码（0成功，1失败）
    private String message;  // 提示信息
    private T data;          // 返回数据（泛型）

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 成功
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    // 失败
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}