package com.times.search.DAO;

/**
 * @author Nishkarsh
 * @date 07-Feb-2019
 *
 * Error data DAO containing all the information in case of exception
 */
public class ErrorDAO {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
