package ru.findFood.rest.exceptions;

import lombok.Data;

@Data
public class AppError {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppError() {
    }

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
