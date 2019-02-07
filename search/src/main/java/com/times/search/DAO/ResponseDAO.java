package com.times.search.DAO;

/**
 * @author Nishkarsh
 * @date 06-Feb-2019
 * @param <T>
 */
public class ResponseDAO<T> {

    private T data;

    private boolean success;

    private String messages;
    private String errorCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseDAO(boolean success) {
        this.success = success;
    }

    public ResponseDAO(boolean success, String message) {
        this(success);
        this.messages = message;
    }

    public ResponseDAO(boolean success, String message, T data) {
        this(success, message);
        this.data = data;
    }

    public static <T> ResponseDAO<T> success(String message) {
        return new ResponseDAO<>(true, message);
    }

    public static <T> ResponseDAO<T> success(String message, T data) {
        return new ResponseDAO<>(true, message, data);
    }

    public static <T> ResponseDAO<T> failure(String message) {
        return new ResponseDAO<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}